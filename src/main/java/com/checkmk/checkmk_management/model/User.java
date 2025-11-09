package com.checkmk.checkmk_management.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
    

    //Das in Service
    public void setPassword(String plainTextPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.passwordHash = encoder.encode(plainTextPassword);
    }
    
    public boolean checkPassword(String plainTextPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(plainTextPassword, this.passwordHash);
    }
}
