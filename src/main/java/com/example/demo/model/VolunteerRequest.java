package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "requestId")
public class VolunteerRequest {
    @Id
    @GeneratedValue
    private Long requestId;

    @ManyToOne
    private Volunteer volunteer;
    private String comments;

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

    private double positionX;
    private double positionY;

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

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

    public enum AvailableTime {
        MORNING,
        AFTERNOON,
        EVENING,
        ALL;
    }

}
