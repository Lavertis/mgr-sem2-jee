package com.pollub.lab_7.exceptions;

public abstract class APIRequestException extends Exception {
    public APIRequestException(String message) {
        super(message);
    }
}