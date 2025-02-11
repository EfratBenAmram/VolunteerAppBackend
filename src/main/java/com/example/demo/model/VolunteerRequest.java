package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class VolunteerRequest {
    @Id
    @GeneratedValue
    private Long requestId;

    @ManyToOne
    @JsonBackReference
    private Volunteer volunteer;
    private String comments;
    private boolean invitationInd;

    @Enumerated(EnumType.STRING)
    private AvailableTime availableTime;
    private LocalDate localDate;
    private LocalDate availableDate;

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<VolunteerType> volunteerTypes;

    public Set<VolunteerType> getVolunteerTypes() {
        return volunteerTypes;
    }

    public void setVolunteerTypes(Set<VolunteerType> volunteerTypes) {
        this.volunteerTypes = volunteerTypes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public AvailableTime getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(AvailableTime availableTime) {
        this.availableTime = availableTime;
    }


    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public boolean isInvitationInd() {
        return invitationInd;
    }

    public void setInvitationInd(boolean invitationInd) {
        this.invitationInd = invitationInd;
    }
    public enum AvailableTime {
        MORNING,
        AFTERNOON,
        EVENING,
        ALL;
    }
}
