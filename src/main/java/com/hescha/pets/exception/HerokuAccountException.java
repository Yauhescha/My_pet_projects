package com.hescha.pets.exception;

public class HerokuAccountException extends RuntimeException {

    public static class HerokuAccountAlreadyContainsThisProjectException extends HerokuAccountException {
    }

    public static class HerokuAccountDoesntHaveThisProject extends HerokuAccountException {
    }
}
