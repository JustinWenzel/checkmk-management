package com.checkmk.checkmk_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checkmk.checkmk_management.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    public boolean existsByEmail(String email);
    
}
