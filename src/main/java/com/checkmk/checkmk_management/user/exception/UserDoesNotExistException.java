package com.checkmk.checkmk_management.user.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String message){
        super(message);
    }
    
}
