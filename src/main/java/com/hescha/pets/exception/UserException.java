package com.hescha.pets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

public class UserException extends AbstractException {
    public UserException(String message) {
        super(message);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class UserWithSameUsernameAlreadyExistsException extends UserException {
        private static final String MESSAGE = "User with username %s already registered. Please use different username.";

        public UserWithSameUsernameAlreadyExistsException(String username) {
            super(format(MESSAGE, username));
        }
    }
}
