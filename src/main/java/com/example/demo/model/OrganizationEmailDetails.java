package com.example.demo.model;

import java.util.List;

public class OrganizationEmailDetails {
    private String name;
    private String region;
    private String orgGoals;
    private List<String> recommendationPhones;
    private List<String> recommendationPdfPaths;

    public OrganizationEmailDetails(String name, String region, String orgGoals,
                                    List<String> recommendationPhones, List<String> recommendationPdfPaths) {
        this.name = name;
        this.region = region;
        this.orgGoals = orgGoals;
        this.recommendationPhones = recommendationPhones;
        this.recommendationPdfPaths = recommendationPdfPaths;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getOrgGoals() { return orgGoals; }
    public void setOrgGoals(String orgGoals) { this.orgGoals = orgGoals; }

    public List<String> getRecommendationPhones() { return recommendationPhones; }
    public void setRecommendationPhones(List<String> recommendationPhones) { this.recommendationPhones = recommendationPhones; }

    public List<String> getRecommendationPdfPaths() { return recommendationPdfPaths; }
    public void setRecommendationPdfPaths(List<String> recommendationPdfPaths) { this.recommendationPdfPaths = recommendationPdfPaths; }

}
