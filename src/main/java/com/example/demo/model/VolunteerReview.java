package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reviewId")
public class VolunteerReview {
    @Id
    @GeneratedValue
    private Long reviewId;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private Volunteer volunteer;

    private String comment;
    private int likes;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public com.example.demo.model.Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(com.example.demo.model.Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
