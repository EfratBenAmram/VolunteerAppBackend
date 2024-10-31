package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    private LocalDate invitationDate;
    private LocalTime responseTime;
    private LocalTime requestTime;

    private String address;//כתובת בה נערכת ההתנדבות
    private String activityDetails;//מידע נוסף על הפעילות
    private String requirements;//דרישות עבור ההתנדבות

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

    public LocalDate getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(LocalDate invitationDate) {
        this.invitationDate = invitationDate;
    }

    public LocalTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalTime responseTime) {
        this.responseTime = responseTime;
    }

    public LocalTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalTime requestTime) {
        this.requestTime = requestTime;
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

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public VolunteerType getVolunteerType() {
        return volunteerType;
    }

    public void setVolunteerType(VolunteerType volunteerType) {
        this.volunteerType = volunteerType;
    }
}
