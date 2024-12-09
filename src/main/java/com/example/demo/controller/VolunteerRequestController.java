package com.example.demo.controller;

import com.example.demo.model.Volunteer;
import com.example.demo.model.VolunteerRequest;
import com.example.demo.model.VolunteerType;
import com.example.demo.service.VolunteerRepository;
import com.example.demo.service.VolunteerRequestRepository;
import com.example.demo.service.VolunteerTypeRepository;
import org.springframework.transaction.annotation.Transactional; // ייבוא מחלקת @Transactional
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/volunteerRequest")
@CrossOrigin
public class VolunteerRequestController {
    private VolunteerRequestRepository volunteerRequestRepository;
    private VolunteerTypeRepository volunteerTypeRepository;

    public VolunteerRequestController(VolunteerRequestRepository volunteerRequestRepository, VolunteerTypeRepository volunteerTypeRepository) {
        this.volunteerRequestRepository = volunteerRequestRepository;
        this.volunteerTypeRepository = volunteerTypeRepository;
    }

    @GetMapping("/volunteerRequest")
    public ResponseEntity<List<VolunteerRequest>> getVolunteerRequest() {
        return new ResponseEntity<>(volunteerRequestRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/volunteerRequestById/{id}")
    public ResponseEntity<VolunteerRequest> getVolunteerRequestByID(@PathVariable Long id) {
        VolunteerRequest volunteerRequest = volunteerRequestRepository.findById(id).orElse(null);
        if (volunteerRequest == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(volunteerRequest, HttpStatus.OK);
    }

    @PostMapping("/addVolunteerRequest")
    @Transactional
    public ResponseEntity<VolunteerRequest> addVolunteerRequest(@RequestBody VolunteerRequest volunteerRequest) {
        Set<VolunteerType> volunteerTypes = new HashSet<>();
        for (VolunteerType volunteerType : volunteerRequest.getVolunteerTypes()) {
            VolunteerType type = volunteerTypeRepository.findById(volunteerType.getVolunteerTypeId()).orElse(null);
            if (type != null) {
                volunteerTypes.add(type);
            }
        }
        volunteerRequest.setVolunteerTypes(volunteerTypes);
        VolunteerRequest request = volunteerRequestRepository.save(volunteerRequest);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }


    @PutMapping("/updateVolunteerRequest/{id}")
    @Transactional
    public ResponseEntity<VolunteerRequest> updateVolunteerRequest(@PathVariable Long id, @RequestBody VolunteerRequest volunteerRequest) {
        VolunteerRequest newVolunteerRequest = volunteerRequestRepository.findById(id).orElse(null);
        if (newVolunteerRequest == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (volunteerRequest.getRequestId() != id) {
            return new ResponseEntity<>(volunteerRequest, HttpStatus.CONFLICT);
        }
        for (VolunteerType volunteerType : volunteerRequest.getVolunteerTypes()) {
            volunteerTypeRepository.save(volunteerType);
        }
        newVolunteerRequest = volunteerRequestRepository.save(volunteerRequest);
        return new ResponseEntity<>(newVolunteerRequest, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteerRequest/{id}")
    public ResponseEntity deleteVolunteerRequest(@PathVariable Long id) {
        VolunteerRequest newVolunteerRequest = volunteerRequestRepository.findById(id).orElse(null);
        if (newVolunteerRequest == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        volunteerRequestRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
