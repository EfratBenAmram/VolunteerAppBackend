package com.example.demo.service;

import com.example.demo.model.EmailDetails;
import com.example.demo.model.OrganizationEmailDetails;

public interface EmailService {
    String sendOrganizationDetails(OrganizationEmailDetails organization);
    String sendEmailWithAttachment(EmailDetails emailDetails);
    void scheduleEmailSending(EmailDetails emailDetails);
}
