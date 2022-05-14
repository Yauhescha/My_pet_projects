package com.hescha.pets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

public class ProjectException extends AbstractException {
    public ProjectException(String message) {
        super(message);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class ProjectWithSameNameAlreadyExists extends ProjectException {
        private static final String MESSAGE = "Project with name %s already exists. Choose another one.";

        public ProjectWithSameNameAlreadyExists(String projectName) {
            super(format(MESSAGE, projectName));
        }
    }
}
