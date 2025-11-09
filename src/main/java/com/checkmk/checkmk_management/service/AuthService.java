package com.checkmk.checkmk_management.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.exception.UserAlreadyExistsException;
import com.checkmk.checkmk_management.model.User;
import com.checkmk.checkmk_management.repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    public boolean registerUser(RegisterFormDTO registerFormDTO){

        if (userRepository.existsByemailAddress(registerFormDTO.getEmailAddress()));{
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = new User();
        newUser.setUsername(registerFormDTO.getUsername());
        newUser.setEmailAddress(registerFormDTO.getEmailAddress());
        newUser.setPassword(encoder.encode(registerFormDTO.getPassword()));

        userRepository.save(newUser);

        return true;


    }
    
}
