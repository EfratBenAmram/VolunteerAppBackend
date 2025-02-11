package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class VolunteerInvitation {

    @Id
    @GeneratedValue
    private Long invitationId;

    @ManyToOne
    private Volunteer volunteer;

    @ManyToOne
    private Organization organization;

    private LocalDateTime invitationDate;
    private LocalDateTime requestTime;

    private String address;//כתובת בה נערכת ההתנדבות
    private String activityDetails;//מידע נוסף על הפעילות
    private String requirements;//דרישות עבור ההתנדבות

    private boolean reviewInd;

    @ManyToOne
    private VolunteerRequest volunteerRequest;

    @ManyToOne
    private VolunteerType volunteerType;//סוג ההתנדבות

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    public enum InvitationStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        COMPLETED
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public LocalDateTime getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(LocalDateTime invitationDate) {
        this.invitationDate = invitationDate;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public boolean isReviewInd() {
        return reviewInd;
    }

    public void setReviewInd(boolean reviewInd) {
        this.reviewInd = reviewInd;
    }

    public VolunteerRequest getVolunteerRequest() {
        return volunteerRequest;
    }

    public void setVolunteerRequest(VolunteerRequest volunteerRequest) {
        this.volunteerRequest = volunteerRequest;
    }

    public VolunteerType getVolunteerType() {
        return volunteerType;
    }

    public void setVolunteerType(VolunteerType volunteerType) {
        this.volunteerType = volunteerType;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
