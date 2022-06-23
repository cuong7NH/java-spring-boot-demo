package com.example.eventuser.exception;

import org.springframework.http.HttpStatus;

public class NotFoundRecordException extends RuntimeException {

    public NotFoundRecordException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
