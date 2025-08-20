package com.openscm.authservice.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message){
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}

