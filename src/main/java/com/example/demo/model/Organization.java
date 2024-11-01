package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Organization {
    @Id
    @GeneratedValue
    private Long organizationId;

    private String name;
    private String email;
    private String password;
    private String orgGoals;
    private String phone;

    private HashMap<String, String> recommendationPhones;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private TopicVolunteer[] topicVolunteers;

//    @JsonIgnore
//    @OneToMany(mappedBy = "organization")
//    private Set<VolunteerReview> VolunteerReview = new HashSet<>();
//
//    public Set<VolunteerReview> getVolunteerReview() {
//        return VolunteerReview;
//    }
//
//    public void setVolunteerReview(Set<VolunteerReview> volunteerReview) {
//        VolunteerReview = volunteerReview;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HashMap<String, String> getRecommendationPhones() {
        return recommendationPhones;
    }

    public void setRecommendationPhones(HashMap<String, String> recommendationPhones) {
        this.recommendationPhones = recommendationPhones;
    }

    public String getOrgGoals() {
        return orgGoals;
    }

    public void setOrgGoals(String orgGoals) {
        this.orgGoals = orgGoals;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
