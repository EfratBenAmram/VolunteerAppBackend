package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
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
    private String phone;
    private String role;
    private String gender;
    private LocalDate birth;
    private boolean experience;
    private boolean isPartOfGroup;
    private int amountVolunteers;

    @Enumerated(EnumType.STRING)
    private Region region;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isPartOfGroup() {
        return isPartOfGroup;
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

    public void setPartOfGroup(boolean partOfGroup) {
        isPartOfGroup = partOfGroup;
    }


}
