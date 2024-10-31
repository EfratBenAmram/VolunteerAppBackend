package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Volunteer {
    @Id
    @GeneratedValue
    private Long volunteerId;

    private String name;
    private String email;
    private String password;
    private String occupation;
    private String sector;
    private int age;
    private String region;
    private String experience;

    @JsonIgnore
    @OneToMany(mappedBy = "volunteer")
    private Set<VolunteerReview> VolunteerReview = new HashSet<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "volunteer")
//    private Set<VolunteerRequest> volunteerRequests = new HashSet<>();
//
//    public Set<VolunteerRequest> getVolunteerRequests() {
//        return volunteerRequests;
//    }
//
//    public void setVolunteerRequests(Set<VolunteerRequest> volunteerRequests) {
//        this.volunteerRequests = volunteerRequests;
//    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Set<com.example.demo.model.VolunteerReview> getVolunteerReview() {
        return VolunteerReview;
    }

    public void setVolunteerReview(Set<com.example.demo.model.VolunteerReview> volunteerReview) {
        VolunteerReview = volunteerReview;
    }
}
