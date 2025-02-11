package com.example.demo.controller;

import com.example.demo.model.VolunteerReview;
import com.example.demo.service.VolunteerReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/volunteerReview")
@CrossOrigin
public class VolunteerReviewController {
    private final VolunteerReviewRepository volunteerReviewRepository;

    public VolunteerReviewController(VolunteerReviewRepository volunteerReviewRepository) {
        this.volunteerReviewRepository = volunteerReviewRepository;
    }

    @GetMapping("/volunteerReview")
    public ResponseEntity<List<VolunteerReview>> getVolunteerReview() {
        try {
            List<VolunteerReview> reviews = volunteerReviewRepository.findAll();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/volunteerReviewById/{id}")
    public ResponseEntity<VolunteerReview> getVolunteerReviewByID(@PathVariable Long id) {
        try {
            VolunteerReview volunteerReview = volunteerReviewRepository.findById(id).orElse(null);
            if (volunteerReview == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(volunteerReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addVolunteerReview")
    public ResponseEntity<VolunteerReview> addVolunteerReview(@RequestBody VolunteerReview volunteerReview) {
        try {
            VolunteerReview newVolunteerReview = volunteerReviewRepository.save(volunteerReview);
            return new ResponseEntity<>(newVolunteerReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateVolunteerReview/{id}")
    public ResponseEntity<VolunteerReview> updateVolunteerReview(@PathVariable Long id, @RequestBody VolunteerReview volunteerReview) {
        try {
            VolunteerReview existingReview = volunteerReviewRepository.findById(id).orElse(null);
            if (existingReview == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            if (volunteerReview.getReviewId() != id) {
                return new ResponseEntity<>(volunteerReview, HttpStatus.CONFLICT);
            }

            VolunteerReview updatedReview = volunteerReviewRepository.save(volunteerReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteVolunteerReview/{id}")
    public ResponseEntity<Void> deleteVolunteerReview(@PathVariable Long id) {
        try {
            VolunteerReview existingReview = volunteerReviewRepository.findById(id).orElse(null);
            if (existingReview == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            volunteerReviewRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
