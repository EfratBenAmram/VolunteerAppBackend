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
    private VolunteerInvitationRepository volunteerInvitationRepository;

    public VolunteerInvitationController(VolunteerInvitationRepository volunteerInvitationRepository) {
        this.volunteerInvitationRepository = volunteerInvitationRepository;
    }
    @GetMapping("/volunteerInvitation")
    public ResponseEntity<List<VolunteerInvitation>> getVolunteerInvitation() {
        return new ResponseEntity<>(volunteerInvitationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/volunteerInvitationById/{id}")
    public ResponseEntity<VolunteerInvitation> getVolunteerInvitationByID(@PathVariable Long id){
        VolunteerInvitation volunteerInvitation = volunteerInvitationRepository.findById(id).orElse(null);
        if(volunteerInvitation == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(volunteerInvitation, HttpStatus.OK);
    }

    @PostMapping("/addVolunteerInvitation")
    public ResponseEntity<VolunteerInvitation> addVolunteerInvitation(@RequestBody VolunteerInvitation volunteerInvitation) {
        VolunteerInvitation newVolunteerInvitation = volunteerInvitationRepository.save(volunteerInvitation);
        return new ResponseEntity<>(newVolunteerInvitation, HttpStatus.CREATED);
    }

    @PutMapping("/updateVolunteerInvitation/{id}")
    public ResponseEntity<VolunteerInvitation> updateVolunteerInvitation( @PathVariable Long id,@RequestBody VolunteerInvitation volunteerInvitation) {
        VolunteerInvitation newVolunteerInvitation = volunteerInvitationRepository.findById(id).orElse(null);
        if(newVolunteerInvitation ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if(!volunteerInvitation.getInvitationId().equals(id)){
            return new ResponseEntity<>(volunteerInvitation, HttpStatus.CONFLICT);
        }
        newVolunteerInvitation =volunteerInvitationRepository.save(volunteerInvitation);
        return new ResponseEntity<>(newVolunteerInvitation, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteerInvitation/{id}")
    public ResponseEntity deleteVolunteerInvitation(@PathVariable Long id){
        VolunteerInvitation newVolunteerInvitation = volunteerInvitationRepository.findById(id).orElse(null);
        if(newVolunteerInvitation ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        volunteerInvitationRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
