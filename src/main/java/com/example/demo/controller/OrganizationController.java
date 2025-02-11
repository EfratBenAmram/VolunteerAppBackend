package com.example.demo.controller;

import com.example.demo.dto.OrganizationDTO;
import com.example.demo.model.Organization;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.MapStruct;
import com.example.demo.service.OrganizationRepository;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/organization")
@CrossOrigin
public class OrganizationController {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    private OrganizationRepository organizationRepository;
    private static String DIRECTORY_URL = System.getProperty("user.dir")+"\\filesAndImages\\";
    private MapStruct mapper;

    public OrganizationController(OrganizationRepository organizationRepository, MapStruct mapStruct) {
        this.organizationRepository = organizationRepository;
        this.mapper = mapStruct;
    }

    @GetMapping("/organization")
    public ResponseEntity<List<Organization>> getOrganizations() {
        try {
            return new ResponseEntity<>(organizationRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/organizationById/{id}")
    public ResponseEntity<Organization> getOrganizationsById(@PathVariable Long id) {
        try {
            Organization newOrganization = organizationRepository.findById(id).orElse(null);
            if (newOrganization == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(newOrganization, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addOrganizations")
    public ResponseEntity<Organization> addOrganizations(@RequestBody Organization organization) {
        try {
            Organization newOrganizations = organizationRepository.save(organization);
            return new ResponseEntity<>(newOrganizations, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateOrganizations/{id}")
    public ResponseEntity<Organization> updateOrganizations(@RequestBody Organization organization, @PathVariable Long id) {
        try {
            Organization newOrganizations = organizationRepository.findById(id).orElse(null);
            if (newOrganizations == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            if (newOrganizations.getOrganizationId() != id) {
                return new ResponseEntity<>(organization, HttpStatus.CONFLICT);
            }
            newOrganizations = organizationRepository.save(organization);
            return new ResponseEntity<>(newOrganizations, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteOrganizations/{id}")
    public ResponseEntity<Void> deleteOrganizations(@PathVariable Long id) {
        try {
            Organization newOrganizations = organizationRepository.findById(id).orElse(null);
            if (newOrganizations == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            organizationRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get1")
    @PreAuthorize("hasRole('USER')")
    public String get() {
        return "hello";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Organization u) {
        try {
            Organization o = organizationRepository.findByName(u.getName());
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(u.getName(), u.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails volunteerDetails = (CustomUserDetails) authentication.getPrincipal();
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(volunteerDetails);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(o);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Organization organization) {
        try {
            organization.setPassword(new BCryptPasswordEncoder(8).encode(organization.getPassword()));
            organization.getRoles().add(roleRepository.findById((long) 2).get());
            organizationRepository.save(organization);
            return ResponseEntity.ok(organization);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut() {
        try {
            ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body("you've been signed out!");
        } catch (Exception e) {
            return new ResponseEntity<>("Error signing out", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadOrganizationWithImage(@RequestPart("organization") Organization organization, @RequestPart("image") MultipartFile image) throws IOException {
        try {
            String imageUrl = DIRECTORY_URL + image.getOriginalFilename();
            Path filePath = Paths.get(imageUrl);
            Files.write(filePath, image.getBytes());
            organization.setImageOrg(imageUrl);
            return signUp(organization);
        } catch (IOException e) {
            return new ResponseEntity<>("Error uploading image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDto/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationDto(@PathVariable long id) throws IOException {
        try {
            Organization organization = organizationRepository.findById(id).orElse(null);
            if (organization == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(mapper.OrganizationToDTO(organization), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
