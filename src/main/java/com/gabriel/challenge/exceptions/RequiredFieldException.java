package com.gabriel.challenge.exceptions;

public class RequiredFieldException extends RuntimeException {

    public RequiredFieldException(String fieldName) {
        super(fieldName + " is required and cannot be null.");
    }
}