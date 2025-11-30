package com.checkmk.checkmk_management.auth;


import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.auth.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.auth.exception.PasswordIsNotEqualException;
import com.checkmk.checkmk_management.role.Role;
import com.checkmk.checkmk_management.role.RoleRepository;
import com.checkmk.checkmk_management.role.exception.RoleNotFoundException;
import com.checkmk.checkmk_management.user.UserMapper;
import com.checkmk.checkmk_management.user.UserRepository;
import com.checkmk.checkmk_management.user.exception.UserAlreadyExistsException;

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
