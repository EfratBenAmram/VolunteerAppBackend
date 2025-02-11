package com.example.demo.security;

import com.example.demo.model.Organization;
import com.example.demo.model.Role;
import com.example.demo.model.Volunteer;
import com.example.demo.service.OrganizationRepository;
import com.example.demo.service.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    VolunteerRepository volunteerRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //לאמת את המשתמש עם המשתמש שנמצא ב-DB
        Volunteer volunteer=volunteerRepository.findByName(username);
        Organization organization = organizationRepository.findByName(username);
        if (volunteer==null && organization==null) {
            throw new UsernameNotFoundException("user not found");
        }
        if(volunteer!=null) {
            List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
            for(Role role:volunteer.getRoles())
            {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
            }
            return new CustomUserDetails(username,volunteer.getPassword(),grantedAuthorities);
        }
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for(Role role:organization.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return new CustomUserDetails(username, organization.getPassword(), grantedAuthorities);
    }
}
