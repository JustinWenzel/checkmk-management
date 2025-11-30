package com.checkmk.checkmk_management.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginFormDTO {

    @NotBlank(message = "Enter a username!")
    private String username;

    @NotBlank(message = "Give a password!")
    private String password;

}
