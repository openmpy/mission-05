package com.example.mission05.global.exception;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    public CustomApiException(String message) {
        super(message);
    }
}