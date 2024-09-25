package com.test_project.demo.advice;

import com.test_project.demo.exception.NumberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity catchResourceNotFoundException(NumberNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
