package com.example.mission05.global.exception;

import lombok.Getter;

@Getter
public class CustomJwtException extends RuntimeException {


    public CustomJwtException(String message) {
        super(message);
    }
}
