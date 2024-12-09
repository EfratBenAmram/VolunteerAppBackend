package com.example.demo.controller;

import com.example.demo.dto.VolunteerDTO;
import com.example.demo.model.Volunteer;
import com.example.demo.service.MapStruct;
import com.example.demo.service.VolunteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("api/volunteer")
@CrossOrigin
public class VolunteerController {
    private VolunteerRepository volunteerRepository;
    private MapStruct mapper;
    private static String DIRECTORY_URL = System.getProperty("user.dir")+"\\filesAndImages\\";

    public VolunteerController(VolunteerRepository volunteerRepository, MapStruct mapStruct) {
        this.volunteerRepository = volunteerRepository;
        this.mapper = mapStruct;
    }

    @GetMapping("/volunteer")
    public ResponseEntity<List<Volunteer>> getVolunteers() {
        return new ResponseEntity<>(volunteerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/volunteerById/{id}")
    public ResponseEntity<Volunteer> getVolunteersById(@PathVariable Long id) {
        Volunteer newVolunteer = volunteerRepository.findById(id).orElse(null);
        if (newVolunteer == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(newVolunteer, HttpStatus.OK);
    }

    @PostMapping("/addVolunteers")
    public ResponseEntity<Volunteer> addVolunteers(@RequestBody Volunteer volunteer) {
        Volunteer newVolunteers = volunteerRepository.save(volunteer);
        return new ResponseEntity<>(newVolunteers, HttpStatus.CREATED);
    }

    @PutMapping("/updateVolunteers/{id}")
    public ResponseEntity<Volunteer> updateVolunteers(@RequestBody Volunteer volunteer, @PathVariable Long id) {
        Volunteer newVolunteers = volunteerRepository.findById(id).orElse(null);
        if (newVolunteers == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (newVolunteers.getVolunteerId() != id) {
            return new ResponseEntity<>(volunteer, HttpStatus.CONFLICT);
        }
        newVolunteers = volunteerRepository.save(volunteer);
        return new ResponseEntity<>(newVolunteers, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteers/{id}")
    public ResponseEntity deleteVolunteers(@PathVariable Long id) {
        Volunteer newVolunteers = volunteerRepository.findById(id).orElse(null);
        if (newVolunteers == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        volunteerRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //--------------------------------------------------------------------------------------------

    @PostMapping("/login")
    public ResponseEntity<Volunteer> login(@RequestBody Volunteer volunteer) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        for (Volunteer u : volunteers) {
            if (volunteer.getPassword().equals(u.getPassword()) && volunteer.getEmail().equals(u.getEmail()))
                return new ResponseEntity<>(u, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Volunteer> signUp(@RequestBody Volunteer volunteer) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        for (Volunteer u : volunteers) {
            if (volunteer.getEmail().equals(u.getEmail())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }
        volunteerRepository.save(volunteer);
        return new ResponseEntity<>(volunteer, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<Volunteer> uploadVolunteerWithImage(@RequestPart("volunteer") Volunteer volunteer, @RequestPart("image") MultipartFile image) throws IOException {
        String imageUrl = DIRECTORY_URL + image.getOriginalFilename();
        Path filePath = Paths.get(imageUrl);
        Files.write(filePath, image.getBytes());
        volunteer.setImageVol(imageUrl);
        return this.signUp(volunteer);
    }

    @GetMapping("/getDto/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerDto(@PathVariable long id)throws IOException{
        return new ResponseEntity<>(mapper.VolunteerToDTO(volunteerRepository.findById(id).orElse(null)),HttpStatus.OK);
    }

}