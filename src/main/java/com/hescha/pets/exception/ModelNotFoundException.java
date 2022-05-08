package com.hescha.pets.exception;

public class ModelNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Not found model with id ";

    public ModelNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
