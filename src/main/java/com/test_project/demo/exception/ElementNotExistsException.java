package com.test_project.demo.exception;

public class ElementNotExistsException extends ArrayIndexOutOfBoundsException {
    public ElementNotExistsException(String element) {
        super("Элемента " + element + " не существует");
    }
}
