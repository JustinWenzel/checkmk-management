package com.checkmk.checkmk_management.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsException(UserAlreadyExistsException exception, Model model){
        model.addAttribute("danger", exception.getMessage());
        return "register";

    }

    @ExceptionHandler(PasswordIsNotEqualException.class)
    public String passwordIsNotEqualException(PasswordIsNotEqualException exception, Model model){
        model.addAttribute("danger", exception.getMessage());
        return "register";
    }

}
