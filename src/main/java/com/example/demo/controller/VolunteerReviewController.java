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
    private VolunteerReviewRepository volunteerReviewRepository;

    public VolunteerReviewController(VolunteerReviewRepository volunteerReviewRepository) {
        this.volunteerReviewRepository = volunteerReviewRepository;
    }
    @GetMapping("/volunteerReview")
    public ResponseEntity<List<VolunteerReview>> getVolunteerReview() {
        return new ResponseEntity<>(volunteerReviewRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/volunteerReviewById/{id}")
    public ResponseEntity<VolunteerReview> getVolunteerReviewByID(@PathVariable Long id){
        VolunteerReview volunteerReview = volunteerReviewRepository.findById(id).orElse(null);
        if(volunteerReview == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(volunteerReview, HttpStatus.OK);
    }

    @PostMapping("/addVolunteerReview")
    public ResponseEntity<VolunteerReview> addVolunteerReview(@RequestBody VolunteerReview volunteerReview) {
        VolunteerReview newVolunteerReview = volunteerReviewRepository.save(volunteerReview);
        return new ResponseEntity<>(newVolunteerReview, HttpStatus.CREATED);
    }

    @PutMapping("/updateVolunteerReview/{id}")
    public ResponseEntity<VolunteerReview> updateVolunteerReview( @PathVariable Long id,@RequestBody VolunteerReview volunteerReview) {
        VolunteerReview newVolunteerReview = volunteerReviewRepository.findById(id).orElse(null);
        if(newVolunteerReview ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if(volunteerReview.getReviewId() != id){
            return new ResponseEntity<>(volunteerReview, HttpStatus.CONFLICT);
        }
        newVolunteerReview =volunteerReviewRepository.save(volunteerReview);
        return new ResponseEntity<>(newVolunteerReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteerReview/{id}")
    public ResponseEntity deleteVolunteerReview(@PathVariable Long id){
        VolunteerReview newVolunteerReview = volunteerReviewRepository.findById(id).orElse(null);
        if(newVolunteerReview ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        volunteerReviewRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
