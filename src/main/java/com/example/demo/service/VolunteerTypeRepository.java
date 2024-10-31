package com.example.demo.service;

import com.example.demo.model.VolunteerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerTypeRepository extends JpaRepository<VolunteerType, Long> {
}
