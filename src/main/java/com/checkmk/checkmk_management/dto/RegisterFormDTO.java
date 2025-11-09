package com.checkmk.checkmk_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterFormDTO {
    
    @NotBlank
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;
    
    @NotBlank
    @Email(message = "Please enter a valid email address")
    private String emailAddress;
    
    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    
    @NotBlank
    private String confirmPassword;
}
