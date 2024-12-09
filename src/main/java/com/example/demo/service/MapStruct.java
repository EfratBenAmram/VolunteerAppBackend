package com.example.demo.service;

import com.example.demo.dto.OrganizationDTO;
import com.example.demo.dto.VolunteerDTO;
import com.example.demo.model.Organization;
import com.example.demo.model.Volunteer;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStruct {

    List<VolunteerDTO> volunteersToDTO(List<Volunteer> volunteers);
    List<OrganizationDTO> organizationsToDTO(List<Organization> organizations);

   default VolunteerDTO VolunteerToDTO(Volunteer volunteer) throws IOException {
       VolunteerDTO volunteerDTO=new VolunteerDTO();
       volunteerDTO.setEmail(volunteer.getEmail());
       volunteerDTO.setName(volunteer.getName());
       volunteerDTO.setPassword(null);
       volunteerDTO.setPhone(volunteer.getPhone());
       volunteerDTO.setRegion(volunteer.getRegion());
       volunteerDTO.setVolunteerId(volunteer.getVolunteerId());
       volunteerDTO.setRole(volunteer.getRole());
       volunteerDTO.setEmail(volunteer.getEmail());
       volunteerDTO.setPassword(volunteer.getPassword());
       volunteerDTO.setGender(volunteer.getGender());
       volunteerDTO.setBirth(volunteer.getBirth());
       volunteerDTO.setExperience(volunteer.getExperience());
       volunteerDTO.setAmountVolunteers(volunteer.getAmountVolunteers());
       volunteerDTO.setVolunteerReview(volunteer.getVolunteerReview());
       volunteerDTO.setImageVol(volunteer.getImageVol());
       volunteerDTO.setVolunteerRequests(volunteer.getVolunteerRequests());
       Path file= Paths.get(volunteer.getImageVol());
       volunteerDTO.setImage(Files.readAllBytes(file));
       return volunteerDTO;
    }

    default OrganizationDTO OrganizationToDTO(Organization organization) throws IOException{
       OrganizationDTO organizationDTO = new OrganizationDTO();
       organizationDTO.setOrganizationId(organization.getOrganizationId());
       organizationDTO.setEmail(organization.getEmail());
       organizationDTO.setName(organization.getName());
       organizationDTO.setPassword(null);
       organizationDTO.setPhone(organization.getPhone());
       organizationDTO.setRegion(organization.getRegion());
       organizationDTO.setOrgGoals(organization.getOrgGoals());
       organizationDTO.setRecommendationPhones(organization.getRecommendationPhones());
       organizationDTO.setImageOrg(organization.getImageOrg());
       Path file = Paths.get(organization.getImageOrg());
       organizationDTO.setImage(Files.readAllBytes(file));
       return organizationDTO;
    }


}
