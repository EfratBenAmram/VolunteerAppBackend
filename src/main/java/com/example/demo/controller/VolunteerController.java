package com.example.demo.controller;

import com.example.demo.dto.VolunteerDTO;
import com.example.demo.model.Volunteer;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.MapStruct;
import com.example.demo.service.RoleRepository;
import com.example.demo.service.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("api/volunteer")
@CrossOrigin
public class VolunteerController {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    private MapStruct mapper;
    private static String DIRECTORY_URL = System.getProperty("user.dir")+"\\filesAndImages\\";

    public VolunteerController(VolunteerRepository volunteerRepository, MapStruct mapStruct) {
        this.volunteerRepository = volunteerRepository;
        this.mapper = mapStruct;
    }

    @GetMapping("/volunteer")
    public ResponseEntity<List<Volunteer>> getVolunteers() {
        try {
            return new ResponseEntity<>(volunteerRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/volunteerById/{id}")
    public ResponseEntity<Volunteer> getVolunteersById(@PathVariable Long id) {
        try {
            Volunteer volunteer = volunteerRepository.findById(id).orElseThrow(() -> new RuntimeException("Volunteer not found"));
            return new ResponseEntity<>(volunteer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addVolunteers")
    public ResponseEntity<Volunteer> addVolunteers(@RequestBody Volunteer volunteer) {
        try {
            Volunteer newVolunteers = volunteerRepository.save(volunteer);
            return new ResponseEntity<>(newVolunteers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateVolunteers/{id}")
    public ResponseEntity<Volunteer> updateVolunteers(@RequestBody Volunteer volunteer, @PathVariable Long id) {
        try {
            Volunteer newVolunteers = volunteerRepository.findById(id).orElse(null);
            if (newVolunteers == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            if (newVolunteers.getVolunteerId() != id) {
                return new ResponseEntity<>(volunteer, HttpStatus.CONFLICT);
            }
            newVolunteers = volunteerRepository.save(volunteer);
            return new ResponseEntity<>(newVolunteers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteVolunteers/{id}")
    public ResponseEntity deleteVolunteers(@PathVariable Long id) {
        try {
            Volunteer newVolunteers = volunteerRepository.findById(id).orElse(null);
            if (newVolunteers == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            volunteerRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting volunteer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Volunteer u) {
        try {
            Volunteer v = volunteerRepository.findByName(u.getName());
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(u.getName(), u.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails volunteerDetails = (CustomUserDetails) authentication.getPrincipal();
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(volunteerDetails);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(v);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid credentials or error during signin", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Volunteer volunteer) {
        try {
            volunteer.setPassword(new BCryptPasswordEncoder(8).encode(volunteer.getPassword()));
            volunteer.getRoles().add(roleRepository.findById((long) 1).get());
            volunteerRepository.save(volunteer);
            return ResponseEntity.ok(volunteer);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during signup", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut() {
        try {
            ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body("you've been signed out!");
        } catch (Exception e) {
            return new ResponseEntity<>("Error during signout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVolunteerWithImage(@RequestPart("volunteer") Volunteer volunteer, @RequestPart("image") MultipartFile image) throws IOException {
        try {
            String imageUrl = DIRECTORY_URL + image.getOriginalFilename();
            Path filePath = Paths.get(imageUrl);
            Files.write(filePath, image.getBytes());
            volunteer.setImageVol(imageUrl);
            return this.signUp(volunteer);
        } catch (IOException e) {
            return new ResponseEntity<>("Error uploading image", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDto/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerDto(@PathVariable long id) throws IOException {
        try {
            return new ResponseEntity<>(mapper.VolunteerToDTO(volunteerRepository.findById(id).orElse(null)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
