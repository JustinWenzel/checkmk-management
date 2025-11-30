package com.checkmk.checkmk_management.user;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.checkmk.checkmk_management.role.Role;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    
    @Column(name = "email_address", nullable = false, unique = true, length = 50)
    private String emailAddress;
    
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    //Set allows no dupes
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Role> roles;
    

}
