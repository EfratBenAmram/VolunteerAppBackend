package com.example.demo.service;

import com.example.demo.model.VolunteerInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerInvitationRepository extends JpaRepository<VolunteerInvitation, Long> {
}
