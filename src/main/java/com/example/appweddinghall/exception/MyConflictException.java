package com.example.appweddinghall.exception;

import org.springframework.http.HttpStatus;

public class MyConflictException extends MyException {
    public MyConflictException() {
        super(HttpStatus.CONFLICT);
    }

    public MyConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
