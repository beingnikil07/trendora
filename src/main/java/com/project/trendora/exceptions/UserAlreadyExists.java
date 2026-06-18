package com.project.trendora.exceptions;

public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists(String message){
        super(message);
    }
}