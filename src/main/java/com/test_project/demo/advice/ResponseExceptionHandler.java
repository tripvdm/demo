package com.test_project.demo.advice;

import com.test_project.demo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*  TODO дописать handlers */
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>("Не найден элемент", HttpStatus.NOT_FOUND);
    }

}
