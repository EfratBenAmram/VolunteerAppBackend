package com.example.demo.service;


import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.PrescriptionDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Doctor;
import com.example.demo.model.Prescription;
import com.example.demo.model.User;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStruct {

    List<UserDto> usersToDto(List<User> users);
    List<PrescriptionDto> prescriptionsToDto(List<Prescription> prescriptions);
    List<DoctorDto> doctorsToDto(List<Doctor> doctors);


   default UserDto UserToDto(User user) throws IOException {
       UserDto userDto=new UserDto();
       userDto.setId(user.getId());
       userDto.setName(user.getName());
       userDto.setEmail(user.getEmail());
       userDto.setPassword(user.getPassword());
       userDto.setB_day(user.getB_day());
       userDto.setTz(user.getTz());
       userDto.setAppointments(user.getAppointments());
       userDto.setPhoneNumber(user.getPhoneNumber());
       userDto.setImagePropile(user.getImagePropile());
       userDto.setPrescriptionList(user.getPrescriptionList());
       userDto.setResponsesList(user.getResponsesList());
       Path file= Paths.get(user.getImagePropile());
       userDto.setImage(Files.readAllBytes(file));
       return userDto;
    }

    default DoctorDto DoctorToDto(Doctor doctor) throws IOException{
       DoctorDto doctorDto = new DoctorDto();
       doctorDto.setId(doctor.getId());
       doctorDto.setName(doctor.getName());
       doctorDto.setEmail(doctor.getEmail());
       doctorDto.setClinicPhone(doctor.getClinicPhone());
       doctorDto.setClinicCity(doctor.getClinicCity());
       doctorDto.setClinicStreet(doctor.getClinicStreet());
       doctorDto.setYearsOfExperience(doctor.getYearsOfExperience());
       doctorDto.setPlaceOfStudy(doctor.getPlaceOfStudy());
       doctorDto.setLicensePlate(doctor.getLicensePlate());
       doctorDto.setImageDoctor(doctor.getImageDoctor());
       doctorDto.setGender(doctor.getGender());
       doctorDto.setAge(doctor.getAge());
       doctorDto.setAppointments(doctor.getAppointments());
       doctorDto.setCategory(doctor.getCategory());
       doctorDto.setPrescriptions(doctor.getPrescriptions());
       doctorDto.setResponses(doctor.getResponses());
       doctorDto.setAppointmentStartAndDayList(doctor.getAppointmentStartAndDayList());
       Path file = Paths.get(doctor.getImageDoctor());
       doctorDto.setImage(Files.readAllBytes(file));
       return doctorDto;
    }

    default PrescriptionDto PrescriptionToDto(Prescription prescription)throws IOException{
       PrescriptionDto presciptionDto=new PrescriptionDto();
       presciptionDto.setId(prescription.getId());
       presciptionDto.setId_doctor(prescription.getDoctor());
       presciptionDto.setId_user(prescription.getId_user());
       presciptionDto.setPrescription(prescription.getPrescription());
       Path file=Paths.get(prescription.getPrescription());
       presciptionDto.setPdfPrescription(Files.readAllBytes(file));
       return presciptionDto;
    }



}
