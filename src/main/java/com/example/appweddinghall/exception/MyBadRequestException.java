package com.example.appweddinghall.exception;

import org.springframework.http.HttpStatus;

public class MyBadRequestException extends MyException {
    public MyBadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public MyBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
