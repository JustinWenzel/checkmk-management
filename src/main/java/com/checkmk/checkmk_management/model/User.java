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
    
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    

    //Das in Service
    public void setPassword(BCryptPasswordEncoder encoder,String plainTextPassword) {
        this.password = encoder.encode(plainTextPassword);
    }
    
    public boolean checkPassword(BCryptPasswordEncoder encoder, String plainTextPassword) {
        return encoder.matches(plainTextPassword, this.password);
    }
}
