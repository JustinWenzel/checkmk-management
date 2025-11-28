package com.checkmk.checkmk_management.mapper;



import java.util.HashSet;
import java.util.Set;
import com.checkmk.checkmk_management.model.Role; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.model.User;

import lombok.RequiredArgsConstructor;

@Component //IS a bean like Service, Repo, Controller
@RequiredArgsConstructor
public class UserMapper {

    private final BCryptPasswordEncoder encoder;
    
    public User toModel(RegisterFormDTO registerForm){

        Set<Role> newRole = new HashSet<>();
        //Convert String to enum
        newRole.add(Role.valueOf(registerForm.getRole()));
        
        User newUser = new User();
        newUser.setUsername(registerForm.getUsername());
        newUser.setEmailAddress(registerForm.getEmailAddress());
        newUser.setPassword(encoder.encode(registerForm.getPassword()));
        newUser.setRoles(newRole);
        

        return newUser;
    }
}
