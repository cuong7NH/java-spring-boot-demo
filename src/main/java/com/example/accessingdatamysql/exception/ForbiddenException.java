package com.example.accessingdatamysql.exception;


public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message){
        super(message);

    }
}
