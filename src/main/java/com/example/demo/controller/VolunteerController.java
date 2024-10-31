package com.example.demo.controller;

import com.example.demo.model.Volunteer;
import com.example.demo.service.VolunteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/volunteer")
@CrossOrigin
public class VolunteerController {
    private VolunteerRepository volunteerRepository;

    public VolunteerController(VolunteerRepository volunteerRepository) {
        this.volunteerRepository= volunteerRepository;
    }

    @GetMapping("/volunteer")
    public ResponseEntity<List<Volunteer>> getVolunteers() {
        return new ResponseEntity<>(volunteerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/volunteerById/{id}")
    public ResponseEntity<Volunteer> getVolunteersById(@PathVariable Long id) {
        Volunteer newVolunteers = volunteerRepository.findById(id).orElse(null);
        if (newVolunteers == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(newVolunteers, HttpStatus.OK);
    }

    @PostMapping("/addVolunteers")
    public ResponseEntity<Volunteer> addVolunteers(@RequestBody Volunteer volunteer) {
        Volunteer newVolunteers = volunteerRepository.save(volunteer);
        return new ResponseEntity<>(newVolunteers, HttpStatus.CREATED);
    }

    @PutMapping("/updateVolunteers/{email}")
    public ResponseEntity<Volunteer> updateVolunteers(@RequestBody Volunteer volunteer, @PathVariable Long id) {
        Volunteer newVolunteers = volunteerRepository.findById(id).orElse(null);
        if (newVolunteers == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (!newVolunteers.getPassword().equals(id)) {
            return new ResponseEntity<>(volunteer, HttpStatus.CONFLICT);
        }
        newVolunteers = volunteerRepository.save(volunteer);
        return new ResponseEntity<>(newVolunteers, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteers/{email}")
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
            if (volunteer.getVolunteerId().equals(u.getName())) {
                if (volunteer.getPassword().equals(u.getPassword()) && volunteer.getEmail().equals(u.getEmail()))
                    return new ResponseEntity<>(u, HttpStatus.OK);
                else
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Volunteer> signUp(@RequestBody Volunteer volunteer) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        for (Volunteer u : volunteers) {
            if (volunteer.getName().equals(u.getName())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }
        volunteerRepository.save(volunteer);
        return new ResponseEntity<>(volunteer, HttpStatus.CREATED);
    }
}
