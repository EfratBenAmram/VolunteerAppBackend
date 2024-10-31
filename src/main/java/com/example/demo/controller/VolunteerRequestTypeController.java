package com.example.demo.controller;

import com.example.demo.model.VolunteerRequestType;
import com.example.demo.service.VolunteerRequestTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/volunteerRequestType")
@CrossOrigin
public class VolunteerRequestTypeController {
    private VolunteerRequestTypeRepository volunteerRequestTypeRepository;

    public VolunteerRequestTypeController(VolunteerRequestTypeRepository volunteerRequestTypeRepository) {
        this.volunteerRequestTypeRepository = volunteerRequestTypeRepository;
    }
    @GetMapping("/volunteerRequestType")
    public ResponseEntity<List<VolunteerRequestType>> getVolunteerRequestType() {
        return new ResponseEntity<>(volunteerRequestTypeRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/volunteerRequestTypeById/{id}")
    public ResponseEntity<VolunteerRequestType> getVolunteerRequestTypeByID(@PathVariable Long id){
        VolunteerRequestType volunteerRequestType = volunteerRequestTypeRepository.findById(id).orElse(null);
        if(volunteerRequestType == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(volunteerRequestType, HttpStatus.OK);
    }

    @PostMapping("/addVolunteerRequestType")
    public ResponseEntity<VolunteerRequestType> addVolunteerRequestType(@RequestBody VolunteerRequestType volunteerRequestType) {
        VolunteerRequestType newVolunteerRequestType = volunteerRequestTypeRepository.save(volunteerRequestType);
        return new ResponseEntity<>(newVolunteerRequestType, HttpStatus.CREATED);
    }

    @PutMapping("/updateVolunteerRequestType/{id}")
    public ResponseEntity<VolunteerRequestType> updateVolunteerRequestType( @PathVariable Long id,@RequestBody VolunteerRequestType volunteerRequestType) {
        VolunteerRequestType newVolunteerRequestType = volunteerRequestTypeRepository.findById(id).orElse(null);
        if(newVolunteerRequestType ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if(volunteerRequestType.getId() != id){
            return new ResponseEntity<>(volunteerRequestType, HttpStatus.CONFLICT);
        }
        newVolunteerRequestType =volunteerRequestTypeRepository.save(volunteerRequestType);
        return new ResponseEntity<>(newVolunteerRequestType, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteerRequestType/{id}")
    public ResponseEntity deleteVolunteerRequestType(@PathVariable Long id){
        VolunteerRequestType newVolunteerRequestType = volunteerRequestTypeRepository.findById(id).orElse(null);
        if(newVolunteerRequestType ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        volunteerRequestTypeRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
