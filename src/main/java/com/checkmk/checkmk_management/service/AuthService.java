package com.checkmk.checkmk_management.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.exception.PasswordIsNotEqualException;
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
    

    public void registerUser(RegisterFormDTO registerFormDTO){

        if (userRepository.existsByemailAddress(registerFormDTO.getEmailAddress())){
            throw new UserAlreadyExistsException("User already exists");
        }

        if (!registerFormDTO.getPassword().equals(registerFormDTO.getConfirmPassword())){
            throw new PasswordIsNotEqualException("The confirmation password doesn't equal the password");
        }

        User newUser = new User();
        newUser.setUsername(registerFormDTO.getUsername());
        newUser.setEmailAddress(registerFormDTO.getEmailAddress());
        newUser.setPassword(encoder.encode(registerFormDTO.getPassword()));

        userRepository.save(newUser);

        


    }
    
}
