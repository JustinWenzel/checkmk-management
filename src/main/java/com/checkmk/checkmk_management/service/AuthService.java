package com.checkmk.checkmk_management.service;


import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.exception.PasswordIsNotEqualException;
import com.checkmk.checkmk_management.exception.RoleNotFoundException;
import com.checkmk.checkmk_management.exception.UserAlreadyExistsException;
import com.checkmk.checkmk_management.mapper.UserMapper;
import com.checkmk.checkmk_management.model.Role;
import com.checkmk.checkmk_management.repository.RoleRepository;
import com.checkmk.checkmk_management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    public void registerUser(RegisterFormDTO registerForm){

        if (userRepository.existsByEmailAddress(registerForm.getEmailAddress())){
            throw new UserAlreadyExistsException("User already exists");
        }

        if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())){
            throw new PasswordIsNotEqualException("The confirmation password doesn't equal the password");
        }

        Role role = roleRepository.findRoleByName(registerForm.getRole()).orElseThrow(() -> new RoleNotFoundException("Role doesn't exist!"));

        userRepository.save(userMapper.toModel(registerForm, role));

    }
    
}
