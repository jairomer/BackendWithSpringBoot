package com.backend.prueba.model.service.exceptions;

public class MissingFieldsException extends Exception {
    public MissingFieldsException(Exception exception) {
        super(exception);
    }
}
