package com.pollub.lab_7.exceptions;

public class NotFoundException extends APIRequestException {
    public NotFoundException(String message) {
        super(message);
    }
}