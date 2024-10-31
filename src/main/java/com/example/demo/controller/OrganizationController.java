package com.example.demo.controller;

import com.example.demo.model.Organization;
import com.example.demo.service.OrganizationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organization")
@CrossOrigin
public class OrganizationController {
    private OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
    @GetMapping("/organization")
    public ResponseEntity<List<Organization>> getOrganization() {
        return new ResponseEntity<>(organizationRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/organizationById/{id}")
    public ResponseEntity<Organization> getOrganizationByID(@PathVariable Long id){
        Organization organization = organizationRepository.findById(id).orElse(null);
        if(organization == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @PostMapping("/addOrganization")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization) {
        Organization newOrganization = organizationRepository.save(organization);
        return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrganization/{id}")
    public ResponseEntity<Organization> updateOrganization( @PathVariable Long id,@RequestBody Organization organization) {
        Organization newOrganization = organizationRepository.findById(id).orElse(null);
        if(newOrganization ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if(organization.getOrganizationId() != id){
            return new ResponseEntity<>(organization, HttpStatus.CONFLICT);
        }
        newOrganization =organizationRepository.save(organization);
        return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteOrganization/{id}")
    public ResponseEntity deleteOrganization(@PathVariable Long id){
        Organization newOrganization = organizationRepository.findById(id).orElse(null);
        if(newOrganization ==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        organizationRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
