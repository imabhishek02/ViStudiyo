package com.vistudiyo.registration.exception;

public class UserNameAlreadyExist extends RuntimeException{
    public UserNameAlreadyExist(String message){
        super(message);
    }
}
