package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class VolunteerRequestType {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private VolunteerRequest volunteerRequest;

    @ManyToOne
    private VolunteerType volunteerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
