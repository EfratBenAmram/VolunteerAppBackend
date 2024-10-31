package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String descriptionOrganizationGoals;
    private String region;
    private String sector;
    private String[] phonesForRecommendation;

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

    public String getDescriptionOrganizationGoals() {
        return descriptionOrganizationGoals;
    }

    public void setDescriptionOrganizationGoals(String descriptionOrganizationGoals) {
        this.descriptionOrganizationGoals = descriptionOrganizationGoals;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getPhonesForRecommendation() {
        return phonesForRecommendation;
    }

    public void setPhonesForRecommendation(String[] phonesForRecommendation) {
        this.phonesForRecommendation = phonesForRecommendation;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

}
