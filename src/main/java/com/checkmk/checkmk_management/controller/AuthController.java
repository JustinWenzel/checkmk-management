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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




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
    public String registerUser(@Valid @ModelAttribute("registerForm") RegisterFormDTO registerForm, BindingResult result, RedirectAttributes redirectAttributes){
        
        if (result.hasErrors()){
            return "register";
        }
        
        authService.registerUser(registerForm);

        redirectAttributes.addFlashAttribute("success", "User " + registerForm.getUsername() + " created successfully");
        return "redirect:/register";

        

    }
    
    
}
