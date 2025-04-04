package com.example.demo.service;

import com.example.demo.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByName(String username);
}
