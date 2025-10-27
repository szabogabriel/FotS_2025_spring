package com.ibm.sk.fots.spring.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TaskToolExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
    ResponseEntity<String> responseEntity = new ResponseEntity<>("Entity with provided ID does not exist.",
        HttpStatus.NOT_FOUND);
    return responseEntity;
  }

}

