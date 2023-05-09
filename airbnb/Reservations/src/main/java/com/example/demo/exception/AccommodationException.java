package com.example.demo.exception;

public class AccommodationException extends RuntimeException {

    public AccommodationException(String message) {
        super(message);
    }

    public AccommodationException(String message, Throwable cause) {
        super(message, cause);
    }
}
