package com.checkmk.checkmk_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.checkmk.checkmk_management.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    
    
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterFormDTO());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registerForm") RegisterFormDTO registerFormDTO, BindingResult result, Model model){
        
        if (result.hasErrors()){
            return "register";
        }
        
        authService.registerUser(registerFormDTO);
        return "redirect:/menu";

        

    }
    
    
}
