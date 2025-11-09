package com.checkmk.checkmk_management.service;

import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.exception.PasswordIsNotEqualException;
import com.checkmk.checkmk_management.exception.UserAlreadyExistsException;
import com.checkmk.checkmk_management.mapper.UserMapper;
import com.checkmk.checkmk_management.repository.UserRepository;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public void registerUser(RegisterFormDTO registerForm){

        if (userRepository.existsByemailAddress(registerForm.getEmailAddress())){
            throw new UserAlreadyExistsException("User already exists");
        }

        if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())){
            throw new PasswordIsNotEqualException("The confirmation password doesn't equal the password");
        }

        userRepository.save(userMapper.toModel(registerForm));

    }
    
}
