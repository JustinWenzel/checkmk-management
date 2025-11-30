package com.checkmk.checkmk_management.auth.exception;

public class PasswordIsNotEqualException extends RuntimeException {

    public PasswordIsNotEqualException(String message){
        super(message);
    }
    
}
