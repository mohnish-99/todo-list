package com.springlearning.io.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.springlearning.io.todolist.exception.ResourceNotFoundException;

@ControllerAdvice
public class TodoContollerAdvise extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(value = ResourceNotFoundException.class)
   // @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleException(ResourceNotFoundException ex, WebRequest req){
    return new ResponseEntity<Object>(
          "Record not found for the given Id",  HttpStatus.NOT_FOUND);
    }
}
