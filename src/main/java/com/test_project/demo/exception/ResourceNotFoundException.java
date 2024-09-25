package com.test_project.demo.exception;

/*  TODO дописать exception */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("Элемента " + message + " не существует");
    }
}
