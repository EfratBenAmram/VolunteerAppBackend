package com.example.demo.model;
import jakarta.persistence.*;
import java.util.HashMap;

@Entity
public class Organization extends User {
    @Id
    @GeneratedValue
    private Long organizationId;
    private String orgGoals;
    private String imageOrg;

    private HashMap<String, String> recommendationPhones;

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getImageOrg() {
        return imageOrg;
    }

    public void setImageOrg(String imageOrg) {
        this.imageOrg = imageOrg;
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
}
