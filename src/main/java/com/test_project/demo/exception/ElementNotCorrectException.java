package com.test_project.demo.exception;

public class ElementNotCorrectException extends RuntimeException {
    public ElementNotCorrectException() {
        super("Элемент не корректен");
    }
}
