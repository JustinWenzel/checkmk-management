package com.checkmk.checkmk_management.exception;

public class PasswordIsNotEqualException extends RuntimeException {

    public PasswordIsNotEqualException(String message){
        super(message);
    }
    
}
