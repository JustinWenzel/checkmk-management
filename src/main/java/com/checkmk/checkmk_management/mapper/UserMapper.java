package com.checkmk.checkmk_management.mapper;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.checkmk.checkmk_management.config.BasicSecurityConfiguration;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.model.User;

@Configuration
public class UserMapper {

    private final BCryptPasswordEncoder encoder;
    
    public User toModel(RegisterFormDTO registerForm){

        User newUser = new User();
        newUser.setUsername(registerForm.getUsername());
        newUser.setEmailAddress(registerForm.getEmailAddress());
        newUser.setPassword(encoder.encode(registerForm.getPassword()));

        return newUser;
    }
}
