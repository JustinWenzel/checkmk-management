package com.checkmk.checkmk_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.checkmk.checkmk_management.dto.RegisterFormDTO;

import org.springframework.ui.Model;


@Controller
public class AuthController {
    
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterFormDTO());
        return "register";
    }
    
}
