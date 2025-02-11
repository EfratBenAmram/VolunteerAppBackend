package com.example.demo.controller;

import com.example.demo.model.OrganizationEmailDetails;
import com.example.demo.model.EmailDetails;
import com.example.demo.service.EmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("api")
@CrossOrigin
@RestController
public class EmailController {
    @Autowired
    private EmailServiceImp emailService;

    @PostMapping("/sendOrganizationDetails")
    public ResponseEntity<String> sendOrganizationDetails(@RequestBody OrganizationEmailDetails organizationEmailDetails) {
        try {
            String result = emailService.sendOrganizationDetails(organizationEmailDetails);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/uploadFiles")
    public ResponseEntity<Map<String, List<String>>> uploadFiles(@RequestParam("file") List<MultipartFile> files) {
        List<String> filePaths = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                System.out.println("Received file: " + file.getOriginalFilename());
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path uploadPath = Paths.get("uploads/");
                Files.createDirectories(uploadPath);
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                filePaths.add(filePath.toAbsolutePath().toString());
            }
            return ResponseEntity.ok(Map.of("filePaths", filePaths));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDetails emailDetails) {
        try {
            String result = emailService.sendEmailWithAttachment(emailDetails);
            if (result.startsWith("Error")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleEmail(@RequestBody EmailDetails emailDetails) {
        try {
            emailService.scheduleEmailSending(emailDetails);
            return ResponseEntity.ok("Email scheduling request received.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to schedule email: " + e.getMessage());
        }
    }


}
