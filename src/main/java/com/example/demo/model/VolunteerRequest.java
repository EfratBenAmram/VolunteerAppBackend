package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    public enum AvailableTime {
        MORNING,
        AFTERNOON,
        EVENING;
    }

}
