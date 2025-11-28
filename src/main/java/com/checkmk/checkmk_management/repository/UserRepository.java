package com.checkmk.checkmk_management.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checkmk.checkmk_management.model.User;



public interface UserRepository extends JpaRepository<User, Long>{
    
    public boolean existsByemailAddress(String email);
    public User findByEmailAddress(String emailAddress);
    
    
}
