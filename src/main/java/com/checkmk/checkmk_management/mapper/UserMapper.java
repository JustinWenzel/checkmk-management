package com.checkmk.checkmk_management.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.model.User;

public class UserMapper {

    private final BCryptPasswordEncoder encoder;
    
    public UserMapper(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
    
    public User toModel(RegisterFormDTO registerForm){

        User newUser = new User();
        newUser.setUsername(registerForm.getUsername());
        newUser.setEmailAddress(registerForm.getEmailAddress());
        newUser.setPassword(encoder.encode(registerForm.getPassword()));

        return newUser;
    }
}
