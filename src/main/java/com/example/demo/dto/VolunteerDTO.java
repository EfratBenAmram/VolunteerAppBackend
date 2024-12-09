package com.example.demo.dto;

import com.example.demo.model.User;
import com.example.demo.model.VolunteerRequest;
import com.example.demo.model.VolunteerReview;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class VolunteerDTO extends User {
    private Long volunteerId;
    private String role;
    private String gender;
    private LocalDate birth;
    private boolean experience;
    private int amountVolunteers;
    private String imageVol;
    private byte[] image;

    private Set<VolunteerReview> VolunteerReview = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "volunteer")
    private Set<VolunteerRequest> volunteerRequests = new HashSet<>();

    public Set<VolunteerRequest> getVolunteerRequests() {
        return volunteerRequests;
    }

    public void setVolunteerRequests(Set<VolunteerRequest> volunteerRequests) {
        this.volunteerRequests = volunteerRequests;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

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

    public boolean getExperience() {
        return experience;
    }

    public void setExperience(boolean experience) {
        this.experience = experience;
    }

    public Set<com.example.demo.model.VolunteerReview> getVolunteerReview() {
        return VolunteerReview;
    }

    public void setVolunteerReview(Set<com.example.demo.model.VolunteerReview> volunteerReview) {
        VolunteerReview = volunteerReview;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isExperience() {
        return experience;
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

}
