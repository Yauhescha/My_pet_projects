package com.hescha.pets.exception;

public class ProjectException extends RuntimeException {
    public static class ProjectWithSameNameAlreadyExists extends ProjectException {
    }
}
