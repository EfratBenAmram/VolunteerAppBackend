package com.example.demo.dto;
import com.example.demo.model.*;

import java.util.HashMap;
import java.util.List;

public class OrganizationDTO extends User {
    private Long organizationId;
    private String orgGoals;
    private HashMap<String, String> recommendationPhones;
    private byte[] image;
    private String imageOrg;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgGoals() {
        return orgGoals;
    }

    public void setOrgGoals(String orgGoals) {
        this.orgGoals = orgGoals;
    }

    public HashMap<String, String> getRecommendationPhones() {
        return recommendationPhones;
    }

    public void setRecommendationPhones(HashMap<String, String> recommendationPhones) {
        this.recommendationPhones = recommendationPhones;
    }
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageOrg() {
        return imageOrg;
    }

    public void setImageOrg(String imageOrg) {
        this.imageOrg = imageOrg;
    }
}
