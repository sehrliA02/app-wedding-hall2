package com.example.appweddinghall.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class MyException extends RuntimeException {
    private final HttpStatus status;
    public MyException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
