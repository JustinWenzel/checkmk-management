package com.checkmk.checkmk_management.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.checkmk.checkmk_management.user.exception.UserDoesNotExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserDoesNotExistException("User does not exist!"));

       String[] roles = user.getRoles()
        .stream()
        .map(role -> role.getName())     
        .toArray(size -> new String[size]); //{ADMIN, READER}

        //Compares password hashes and creates a session with this user
        return org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword())
        .roles(roles).build();
    }
}
