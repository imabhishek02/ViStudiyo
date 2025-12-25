package com.vistudiyo.registration.exception;

public class InValidPasswordEx extends RuntimeException{
    public InValidPasswordEx(String message){
        super(message);
    }
}
