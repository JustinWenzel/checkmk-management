package com.checkmk.checkmk_management.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.exception.UserDoesNotExistException;
import com.checkmk.checkmk_management.model.User;
import com.checkmk.checkmk_management.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UserDoesNotExistException("User does not exist!");
        }

       String[] roles = user.getRoles()
        .stream()
        .map(role -> role.name())           
        .toArray(size -> new String[size]); 

        //Compares passwords and creates a session with this user
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).roles(roles).build();
    }
}
