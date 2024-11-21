package com.example.demo.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;

@Entity
public class Organization extends User {
    @Id
    @GeneratedValue
    private Long organizationId;
    private String orgGoals;
    private String imageOrg;

    private HashMap<String, String> recommendationPhones;

    @ElementCollection(targetClass = TopicVolunteer.class)
    @CollectionTable(name = "organization_topics", joinColumns = @JoinColumn(name = "organization_id"))
    @Column(name = "topic_volunteer")
    @Enumerated(EnumType.STRING)
    private List<TopicVolunteer> topicVolunteers;

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

    public List<TopicVolunteer> getTopicVolunteers() {
        return topicVolunteers;
    }

    public void setTopicVolunteers(List<TopicVolunteer> topicVolunteers) {
        this.topicVolunteers = topicVolunteers;
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

}
