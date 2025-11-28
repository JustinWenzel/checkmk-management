package com.checkmk.checkmk_management.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsException(UserAlreadyExistsException exception, RedirectAttributes redirectAttributes) {
        
        redirectAttributes.addFlashAttribute("danger", exception.getMessage());
        return "redirect:/register";
    }

    @ExceptionHandler(PasswordIsNotEqualException.class)
    public String passwordIsNotEqualException(PasswordIsNotEqualException exception, RedirectAttributes redirectAttributes) {
        
        redirectAttributes.addFlashAttribute("danger", exception.getMessage());
        return "redirect:/register";
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public String userDoesNotExistException(UserDoesNotExistException exception, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("danger", exception.getMessage());
        return "redirect:/login";
    }
}
