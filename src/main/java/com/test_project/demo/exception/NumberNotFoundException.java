package com.test_project.demo.exception;

public class NumberNotFoundException extends RuntimeException {
    public NumberNotFoundException(String message) {
        super(message);
    }
}
