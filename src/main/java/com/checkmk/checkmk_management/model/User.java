package com.checkmk.checkmk_management.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    

}
