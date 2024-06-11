package com.bookstore.exception;

@SuppressWarnings("serial")
public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) {
        super(message);
    }
}