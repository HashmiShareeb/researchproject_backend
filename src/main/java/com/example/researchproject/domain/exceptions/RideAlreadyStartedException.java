package com.example.researchproject.domain.exceptions;

public class RideAlreadyStartedException extends RuntimeException {
    public RideAlreadyStartedException(String message) {
        super(message);
    }
}
