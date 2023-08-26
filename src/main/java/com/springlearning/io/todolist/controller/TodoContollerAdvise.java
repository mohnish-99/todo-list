package com.springlearning.io.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.springlearning.io.todolist.exception.MethodArgumentNotValidException;
import com.springlearning.io.todolist.exception.ResourceAlreadyPresentException;
import com.springlearning.io.todolist.exception.ResourceNotFoundException;

@ControllerAdvice
public class TodoContollerAdvise extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(value = ResourceNotFoundException.class)
   // @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleException(ResourceNotFoundException ex, WebRequest req){
    return new ResponseEntity<Object>(
          "Record not found for the given Id",  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
   // @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleBadRequestException(MethodArgumentNotValidException ex, WebRequest req){

        return new ResponseEntity<Object>(
          "Name or Description cannot be empty or null",  HttpStatus.BAD_REQUEST);
    
    }

    @ExceptionHandler(value = ResourceAlreadyPresentException.class)
   // @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleDuplicateDataException(ResourceAlreadyPresentException ex, WebRequest req){

        return new ResponseEntity<Object>(
          "Task already exists with the given name",  HttpStatus.CONFLICT);
    
    }
}
