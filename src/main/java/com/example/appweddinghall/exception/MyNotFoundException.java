package com.example.appweddinghall.exception;

import org.springframework.http.HttpStatus;

public class MyNotFoundException extends MyException {
    public MyNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public MyNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
