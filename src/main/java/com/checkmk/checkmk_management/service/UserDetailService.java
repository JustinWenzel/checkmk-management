package com.checkmk.checkmk_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.model.User;
import com.checkmk.checkmk_management.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmailAddress(email).orElseThrow(() -> new UserDoesNotExistException("User does not exist!"));


        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getRoles().stream().map(role -> role.name()).toArray(size -> new String[size])).build();
    }
    
}
