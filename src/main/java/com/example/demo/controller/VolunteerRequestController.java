package com.example.demo.controller;

import com.example.demo.model.VolunteerRequest;
import com.example.demo.service.VolunteerRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/volunteerRequest")
@CrossOrigin
public class VolunteerRequestController {
    private VolunteerRequestRepository volunteerRequestRepository;

    public VolunteerRequestController(VolunteerRequestRepository volunteerRequestRepository) {
        this.volunteerRequestRepository = volunteerRequestRepository;
    }
    @GetMapping("/volunteerRequest")
    public ResponseEntity<List<VolunteerRequest>> getVolunteerRequest() {
        return new ResponseEntity<>(volunteerRequestRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/volunteerRequestById/{id}")
    public ResponseEntity<VolunteerRequest> getVolunteerRequestByID(@PathVariable Long id){
        VolunteerRequest volunteerRequest = volunteerRequestRepository.findById(id).orElse(null);
        if(volunteerRequest == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(volunteerRequest, HttpStatus.OK);
    }

    @PostMapping("/addVolunteerRequest")
    public ResponseEntity<VolunteerRequest> addVolunteerRequest(@RequestBody VolunteerRequest volunteerRequest) {
        VolunteerRequest newVolunteerRequest = volunteerRequestRepository.save(volunteerRequest);
        return new ResponseEntity<>(newVolunteerRequest, HttpStatus.CREATED);
    }

    @PutMapping("/updateVolunteerRequest/{id}")
    public ResponseEntity<VolunteerRequest> updateVolunteerRequest( @PathVariable Long id,@RequestBody VolunteerRequest volunteerRequest) {
        VolunteerRequest newVolunteerRequest = volunteerRequestRepository.findById(id).orElse(null);
        if(newVolunteerRequest ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if(volunteerRequest.getRequestId() != id){
            return new ResponseEntity<>(volunteerRequest, HttpStatus.CONFLICT);
        }
        newVolunteerRequest =volunteerRequestRepository.save(volunteerRequest);
        return new ResponseEntity<>(newVolunteerRequest, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteerRequest/{id}")
    public ResponseEntity deleteVolunteerRequest(@PathVariable Long id){
        VolunteerRequest newVolunteerRequest = volunteerRequestRepository.findById(id).orElse(null);
        if(newVolunteerRequest ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        volunteerRequestRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
