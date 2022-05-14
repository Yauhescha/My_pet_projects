package com.hescha.pets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

public class HerokuAccountException extends AbstractException {
    public HerokuAccountException(String message) {
        super(message);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class HerokuAccountAlreadyContainsThisProjectException extends HerokuAccountException {
        private static final String MESSAGE = "Heroku Accoun already contains Project '%s'.";

        public HerokuAccountAlreadyContainsThisProjectException(String projectName) {
            super(format(MESSAGE, projectName));
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class HerokuAccountDoesntHaveThisProject extends HerokuAccountException {
        private static final String MESSAGE = "You cannot delete project '%s', because Heroku Account does not contains it.";

        public HerokuAccountDoesntHaveThisProject(String projectName) {
            super(format(MESSAGE, projectName));
        }
    }
}
