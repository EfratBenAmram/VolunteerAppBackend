package com.example.demo.service;

import com.example.demo.model.VolunteerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRequestRepository extends JpaRepository<VolunteerRequest, Long> {
}
