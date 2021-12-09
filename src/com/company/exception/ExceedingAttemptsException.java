package com.company.exception;

public class ExceedingAttemptsException extends Exception {

    public ExceedingAttemptsException(String message) {
        super(message);
    }
}
