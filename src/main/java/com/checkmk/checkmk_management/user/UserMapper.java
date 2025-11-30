package com.checkmk.checkmk_management.user;



import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.checkmk.checkmk_management.auth.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.role.Role;

import lombok.RequiredArgsConstructor;

@Component //IS a bean like Service, Repo, Controller
@RequiredArgsConstructor
public class UserMapper {

    private final BCryptPasswordEncoder encoder;
    
    public User toModel(RegisterFormDTO registerForm, Role role){

        Set<Role> newRole = new HashSet<>();
        newRole.add(role);
        
        User newUser = new User();
        newUser.setUsername(registerForm.getUsername());
        newUser.setEmailAddress(registerForm.getEmailAddress());
        newUser.setPassword(encoder.encode(registerForm.getPassword()));
        newUser.setRoles(newRole);
        

        return newUser;
    }
}
