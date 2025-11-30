package com.checkmk.checkmk_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import com.checkmk.checkmk_management.model.User;



public interface UserRepository extends JpaRepository<User, Long>{
    
    public boolean existsByEmailAddress(String email);

    //Custom query
    @NativeQuery("SELECT * FROM USERS WHERE USERNAME = ?1")
    public Optional<User> findByUsername(String name);
    
    
}
