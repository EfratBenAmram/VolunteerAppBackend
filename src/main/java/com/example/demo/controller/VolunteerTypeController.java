package com.example.demo.controller;

import com.example.demo.model.VolunteerType;
import com.example.demo.service.VolunteerTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/volunteerType")
@CrossOrigin
public class VolunteerTypeController {
    private final VolunteerTypeRepository volunteerTypeRepository;

    public VolunteerTypeController(VolunteerTypeRepository volunteerTypeRepository) {
        this.volunteerTypeRepository = volunteerTypeRepository;
    }

    @GetMapping("/volunteerType")
    public ResponseEntity<List<VolunteerType>> getVolunteerType() {
        try {
            List<VolunteerType> volunteerTypes = volunteerTypeRepository.findAll();
            return new ResponseEntity<>(volunteerTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/volunteerTypeById/{id}")
    public ResponseEntity<VolunteerType> getVolunteerTypeByID(@PathVariable Long id) {
        try {
            VolunteerType volunteerType = volunteerTypeRepository.findById(id).orElse(null);
            if (volunteerType == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(volunteerType, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addVolunteerType")
    public ResponseEntity<VolunteerType> addVolunteerType(@RequestBody VolunteerType volunteerType) {
        try {
            VolunteerType newVolunteerType = volunteerTypeRepository.save(volunteerType);
            return new ResponseEntity<>(newVolunteerType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateVolunteerType/{id}")
    public ResponseEntity<VolunteerType> updateVolunteerType(@PathVariable Long id, @RequestBody VolunteerType volunteerType) {
        try {
            VolunteerType existingVolunteerType = volunteerTypeRepository.findById(id).orElse(null);
            if (existingVolunteerType == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            if (volunteerType.getVolunteerTypeId() != id) {
                return new ResponseEntity<>(volunteerType, HttpStatus.CONFLICT);
            }

            VolunteerType updatedVolunteerType = volunteerTypeRepository.save(volunteerType);
            return new ResponseEntity<>(updatedVolunteerType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteVolunteerType/{id}")
    public ResponseEntity<Void> deleteVolunteerType(@PathVariable Long id) {
        try {
            VolunteerType existingVolunteerType = volunteerTypeRepository.findById(id).orElse(null);
            if (existingVolunteerType == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            volunteerTypeRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
