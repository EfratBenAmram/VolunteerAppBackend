package com.example.demo.controller;

import com.example.demo.model.VolunteerRequest;
import com.example.demo.model.VolunteerType;
import com.example.demo.service.VolunteerRequestRepository;
import com.example.demo.service.VolunteerTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/volunteerRequest")
@CrossOrigin
public class VolunteerRequestController {

    private final VolunteerRequestRepository volunteerRequestRepository;
    private final VolunteerTypeRepository volunteerTypeRepository;

    public VolunteerRequestController(VolunteerRequestRepository volunteerRequestRepository, VolunteerTypeRepository volunteerTypeRepository) {
        this.volunteerRequestRepository = volunteerRequestRepository;
        this.volunteerTypeRepository = volunteerTypeRepository;
    }

    @GetMapping("/volunteerRequest")
    public ResponseEntity<List<VolunteerRequest>> getVolunteerRequest() {
        try {
            List<VolunteerRequest> volunteerRequests = volunteerRequestRepository.findAll();
            return new ResponseEntity<>(volunteerRequests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/volunteerRequestById/{id}")
    public ResponseEntity<VolunteerRequest> getVolunteerRequestByID(@PathVariable Long id) {
        try {
            VolunteerRequest volunteerRequest = volunteerRequestRepository.findById(id).orElse(null);
            if (volunteerRequest == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(volunteerRequest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addVolunteerRequest")
    @Transactional
    public ResponseEntity<VolunteerRequest> addVolunteerRequest(@RequestBody VolunteerRequest volunteerRequest) {
        try {
            Set<VolunteerType> volunteerTypes = new HashSet<>();
            for (VolunteerType volunteerType : volunteerRequest.getVolunteerTypes()) {
                VolunteerType type = volunteerTypeRepository.findById(volunteerType.getVolunteerTypeId()).orElse(null);
                if (type != null) {
                    volunteerTypes.add(type);
                }
            }
            volunteerRequest.setVolunteerTypes(volunteerTypes);
            VolunteerRequest savedRequest = volunteerRequestRepository.save(volunteerRequest);
            return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateVolunteerRequest/{id}")
    @Transactional
    public ResponseEntity<VolunteerRequest> updateVolunteerRequest(@PathVariable Long id, @RequestBody VolunteerRequest volunteerRequest) {
        try {
            VolunteerRequest existingRequest = volunteerRequestRepository.findById(id).orElse(null);
            if (existingRequest == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            if (!volunteerRequest.getRequestId().equals(id)) {
                return new ResponseEntity<>(volunteerRequest, HttpStatus.CONFLICT);
            }

            for (VolunteerType volunteerType : volunteerRequest.getVolunteerTypes()) {
                volunteerTypeRepository.save(volunteerType);
            }

            VolunteerRequest updatedRequest = volunteerRequestRepository.save(volunteerRequest);
            return new ResponseEntity<>(updatedRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteVolunteerRequest/{id}")
    public ResponseEntity<?> deleteVolunteerRequest(@PathVariable Long id) {
        try {
            VolunteerRequest existingRequest = volunteerRequestRepository.findById(id).orElse(null);
            if (existingRequest == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            volunteerRequestRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
