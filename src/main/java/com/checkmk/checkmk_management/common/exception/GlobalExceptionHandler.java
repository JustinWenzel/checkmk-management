package com.checkmk.checkmk_management.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.checkmk.checkmk_management.auth.exception.PasswordIsNotEqualException;
import com.checkmk.checkmk_management.common.config.CheckmkServerException;
import com.checkmk.checkmk_management.role.exception.RoleNotFoundException;
import com.checkmk.checkmk_management.user.exception.UserAlreadyExistsException;
import com.checkmk.checkmk_management.user.exception.UserDoesNotExistException;

//Catches all errors
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
        redirectAttributes.addFlashAttribute("danger", exception.getMessage()); //Uses message from service and adds it to flash message
        return "redirect:/login";
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public String roleNotFoundException(RoleNotFoundException exception, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("danger", exception.getMessage());
        return "redirect:/register";
    }

    @ExceptionHandler(CheckmkClientException.class)
    public String checkmkClientException(CheckmkClientException exception, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("danger", exception.getMessage());
        return "redirect:/menu";
    }

    @ExceptionHandler(CheckmkServerException.class)
    public String checkmkServerException(CheckmkServerException exception, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("danger", exception.getMessage());
        return "redirect:/menu";
    }

}
