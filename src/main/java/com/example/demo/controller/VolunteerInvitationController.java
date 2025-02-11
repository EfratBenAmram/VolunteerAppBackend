package com.example.demo.controller;

import com.example.demo.model.VolunteerInvitation;
import com.example.demo.service.VolunteerInvitationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/volunteerInvitation")
@CrossOrigin
public class VolunteerInvitationController {
    private final VolunteerInvitationRepository volunteerInvitationRepository;

    public VolunteerInvitationController(VolunteerInvitationRepository volunteerInvitationRepository) {
        this.volunteerInvitationRepository = volunteerInvitationRepository;
    }

    @GetMapping("/volunteerInvitation")
    public ResponseEntity<List<VolunteerInvitation>> getVolunteerInvitation() {
        try {
            List<VolunteerInvitation> volunteerInvitations = volunteerInvitationRepository.findAll();
            return new ResponseEntity<>(volunteerInvitations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/volunteerInvitationById/{id}")
    public ResponseEntity<VolunteerInvitation> getVolunteerInvitationByID(@PathVariable Long id) {
        try {
            VolunteerInvitation volunteerInvitation = volunteerInvitationRepository.findById(id).orElse(null);
            if (volunteerInvitation == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(volunteerInvitation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addVolunteerInvitation")
    public ResponseEntity<VolunteerInvitation> addVolunteerInvitation(@RequestBody VolunteerInvitation volunteerInvitation) {
        try {
            VolunteerInvitation newVolunteerInvitation = volunteerInvitationRepository.save(volunteerInvitation);
            return new ResponseEntity<>(newVolunteerInvitation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateVolunteerInvitation/{id}")
    public ResponseEntity<VolunteerInvitation> updateVolunteerInvitation(@PathVariable Long id, @RequestBody VolunteerInvitation volunteerInvitation) {
        try {
            VolunteerInvitation existingVolunteerInvitation = volunteerInvitationRepository.findById(id).orElse(null);
            if (existingVolunteerInvitation == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            if (!volunteerInvitation.getInvitationId().equals(id)) {
                return new ResponseEntity<>(volunteerInvitation, HttpStatus.CONFLICT);
            }

            VolunteerInvitation updatedVolunteerInvitation = volunteerInvitationRepository.save(volunteerInvitation);
            return new ResponseEntity<>(updatedVolunteerInvitation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteVolunteerInvitation/{id}")
    public ResponseEntity<Void> deleteVolunteerInvitation(@PathVariable Long id) {
        try {
            VolunteerInvitation existingVolunteerInvitation = volunteerInvitationRepository.findById(id).orElse(null);
            if (existingVolunteerInvitation == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            volunteerInvitationRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
