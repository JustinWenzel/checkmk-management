package com.checkmk.checkmk_management.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(String message){
        super(message);
    }
    
}
