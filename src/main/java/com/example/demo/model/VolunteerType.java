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

    @JsonIgnore
    @OneToMany(mappedBy = "volunteerType")
    private Set<VolunteerRequestType> volunteerRequestTypes = new HashSet<>();

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
    public Set<VolunteerRequestType> getVolunteerRequestTypes() {
        return volunteerRequestTypes;
    }

    public void setVolunteerRequestTypes(Set<VolunteerRequestType> volunteerRequestTypes) {
        this.volunteerRequestTypes = volunteerRequestTypes;
    }

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

}
