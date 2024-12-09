package com.example.demo.controller;

import com.example.demo.dto.OrganizationDTO;
import com.example.demo.model.Organization;
import com.example.demo.service.MapStruct;
import com.example.demo.service.OrganizationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/organization")
@CrossOrigin
public class OrganizationController {
    private OrganizationRepository organizationRepository;
    private static String DIRECTORY_URL = System.getProperty("user.dir")+"\\filesAndImages\\";
    private MapStruct mapper;

    public OrganizationController(OrganizationRepository organizationRepository, MapStruct mapStruct) {
        this.organizationRepository = organizationRepository;
        this.mapper = mapStruct;
    }

    @GetMapping("/organization")
    public ResponseEntity<List<Organization>> getOrganizations() {
        return new ResponseEntity<>(organizationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/organizationById/{id}")
    public ResponseEntity<Organization> getOrganizationsById(@PathVariable Long id) {
        Organization newOrganization = organizationRepository.findById(id).orElse(null);
        if (newOrganization == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(newOrganization, HttpStatus.OK);
    }

    @PostMapping("/addOrganizations")
    public ResponseEntity<Organization> addOrganizations(@RequestBody Organization organization) {
        Organization newOrganizations = organizationRepository.save(organization);
        return new ResponseEntity<>(newOrganizations, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrganizations/{id}")
    public ResponseEntity<Organization> updateOrganizations(@RequestBody Organization organization, @PathVariable Long id) {
        Organization newOrganizations = organizationRepository.findById(id).orElse(null);
        if (newOrganizations == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (newOrganizations.getOrganizationId() != id) {
            return new ResponseEntity<>(organization, HttpStatus.CONFLICT);
        }
        newOrganizations = organizationRepository.save(organization);
        return new ResponseEntity<>(newOrganizations, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteOrganizations/{id}")
    public ResponseEntity deleteOrganizations(@PathVariable Long id) {
        Organization newOrganizations = organizationRepository.findById(id).orElse(null);
        if (newOrganizations == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        organizationRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //--------------------------------------------------------------------------------------------

    @PostMapping("/login")
    public ResponseEntity<Organization> login(@RequestBody Organization organization) {
        List<Organization> organizations = organizationRepository.findAll();
        for (Organization u : organizations) {
            if (organization.getPassword().equals(u.getPassword()) && organization.getEmail().equals(u.getEmail()))
                return new ResponseEntity<>(u, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Organization> signUp(@RequestBody Organization organization) {
        List<Organization> organizations = organizationRepository.findAll();
        for (Organization u : organizations) {
            if (organization.getEmail().equals(u.getEmail())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }
        organizationRepository.save(organization);
        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<Organization> uploadOrganizationWithImage(@RequestPart("organization") Organization organization, @RequestPart("image") MultipartFile image) throws IOException {
        String imageUrl = DIRECTORY_URL + image.getOriginalFilename();
        Path filePath = Paths.get(imageUrl);
        Files.write(filePath, image.getBytes());
        organization.setImageOrg(imageUrl);
        return signUp(organization);
    }

    @GetMapping("/getDto/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationDto(@PathVariable long id)throws IOException{
        return new ResponseEntity<>(mapper.OrganizationToDTO(organizationRepository.findById(id).orElse(null)),HttpStatus.OK);
    }
}
