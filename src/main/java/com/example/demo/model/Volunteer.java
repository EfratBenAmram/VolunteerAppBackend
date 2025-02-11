package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Volunteer extends User {

    @Id
    @GeneratedValue
    private Long volunteerId;

    private String role;
    private String gender;
    private LocalDate birth;
    private boolean experience;
    private int amountVolunteers;
    private String imageVol;
    private String city;

    @JsonManagedReference
    @OneToMany(mappedBy = "volunteer")
    private Set<VolunteerReview> VolunteerReview = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "volunteer")
    private Set<VolunteerRequest> volunteerRequests = new HashSet<>();

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public boolean isExperience() {
        return experience;
    }

    public void setExperience(boolean experience) {
        this.experience = experience;
    }

    public int getAmountVolunteers() {
        return amountVolunteers;
    }

    public void setAmountVolunteers(int amountVolunteers) {
        this.amountVolunteers = amountVolunteers;
    }

    public String getImageVol() {
        return imageVol;
    }

    public void setImageVol(String imageVol) {
        this.imageVol = imageVol;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<com.example.demo.model.VolunteerReview> getVolunteerReview() {
        return VolunteerReview;
    }

    public void setVolunteerReview(Set<com.example.demo.model.VolunteerReview> volunteerReview) {
        VolunteerReview = volunteerReview;
    }

    public Set<VolunteerRequest> getVolunteerRequests() {
        return volunteerRequests;
    }

    public void setVolunteerRequests(Set<VolunteerRequest> volunteerRequests) {
        this.volunteerRequests = volunteerRequests;
    }
}
