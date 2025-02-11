package com.example.demo.service;

import com.example.demo.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Volunteer findByName(String name);


}
