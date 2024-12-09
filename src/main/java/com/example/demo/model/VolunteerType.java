package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class VolunteerType {
    @Id
    @GeneratedValue
    private Long volunteerTypeId;

    private String name;
    private int minAge;
    private int maxAge;

    @JsonIgnore
    @ManyToMany(mappedBy = "volunteerTypes")
    private Set<VolunteerRequest> volunteerRequests = new HashSet<>();

    public Long getVolunteerTypeId() {
        return volunteerTypeId;
    }

    public void setVolunteerTypeId(Long volunteerTypeId) {
        this.volunteerTypeId = volunteerTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }


    public Set<VolunteerRequest> getVolunteerRequests() {
        return volunteerRequests;
    }

    public void setVolunteerRequests(Set<VolunteerRequest> volunteerRequests) {
        this.volunteerRequests = volunteerRequests;
    }

    /*
    @JsonIgnore
    @OneToMany(mappedBy = "volunteerType")
    private Set<VolunteerInvitation> volunteerInvitation = new HashSet<>();

    public Set<VolunteerInvitation> getVolunteerInvitation() {
        return volunteerInvitation;
    }

    public void setVolunteerInvitation(Set<VolunteerInvitation> volunteerInvitation) {
        this.volunteerInvitation = volunteerInvitation;
    }
    */
}
