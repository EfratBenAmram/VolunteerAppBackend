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
    private VolunteerTypeRepository volunteerTypeRepository;

    public VolunteerTypeController(VolunteerTypeRepository volunteerTypeRepository) {
        this.volunteerTypeRepository = volunteerTypeRepository;
    }
    @GetMapping("/volunteerType")
    public ResponseEntity<List<VolunteerType>> getVolunteerType() {
        return new ResponseEntity<>(volunteerTypeRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/volunteerTypeById/{id}")
    public ResponseEntity<VolunteerType> getVolunteerTypeByID(@PathVariable Long id){
        VolunteerType volunteerType = volunteerTypeRepository.findById(id).orElse(null);
        if(volunteerType == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(volunteerType, HttpStatus.OK);
    }

    @PostMapping("/addVolunteerType")
    public ResponseEntity<VolunteerType> addVolunteerType(@RequestBody VolunteerType volunteerType) {
        VolunteerType newVolunteerType = volunteerTypeRepository.save(volunteerType);
        return new ResponseEntity<>(newVolunteerType, HttpStatus.CREATED);
    }

    @PutMapping("/updateVolunteerType/{id}")
    public ResponseEntity<VolunteerType> updateVolunteerType( @PathVariable Long id,@RequestBody VolunteerType volunteerType) {
        VolunteerType newVolunteerType = volunteerTypeRepository.findById(id).orElse(null);
        if(newVolunteerType ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if(volunteerType.getVolunteerTypeId() != id){
            return new ResponseEntity<>(volunteerType, HttpStatus.CONFLICT);
        }
        newVolunteerType =volunteerTypeRepository.save(volunteerType);
        return new ResponseEntity<>(newVolunteerType, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVolunteerType/{id}")
    public ResponseEntity deleteVolunteerType(@PathVariable Long id){
        VolunteerType newVolunteerType = volunteerTypeRepository.findById(id).orElse(null);
        if(newVolunteerType ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        volunteerTypeRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
