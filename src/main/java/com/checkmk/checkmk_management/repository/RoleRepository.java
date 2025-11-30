package com.checkmk.checkmk_management.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.checkmk.checkmk_management.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    //Could return null
    @NativeQuery("SELECT * FROM ROLE WHERE NAME = ?1")
    public Optional<Role> findRoleByName(String name);
    
}
