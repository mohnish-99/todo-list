package com.springlearning.io.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.springlearning.io.todolist.exception.MethodArgumentNotValidException;
import com.springlearning.io.todolist.exception.ResourceAlreadyPresentException;
import com.springlearning.io.todolist.exception.ResourceNotFoundException;

@RestControllerAdvice
public class TodoContollerAdvise extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String,Object> handleException(ResourceNotFoundException ex, WebRequest req){
        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.NOT_FOUND);
        errorMap.put("cause", "ResourceNotFoundException");
        errorMap.put("message", ex.getLocalizedMessage());
        
        return errorMap;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleBadRequestException(MethodArgumentNotValidException ex, WebRequest req){

        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST);
        errorMap.put("cause", ex.getCause());
        errorMap.put("message", ex.getMessage());
        errorMap.put("exception", ex.getStackTrace());
        
        return errorMap;
        
    
    }

    @ExceptionHandler(value = ResourceAlreadyPresentException.class)
    //@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String,Object> handleDuplicateDataException(ResourceAlreadyPresentException ex, WebRequest req){

        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.CONFLICT);
        errorMap.put("cause", "ResourceAlreadyPresentException");
        errorMap.put("message", ex.getMessage());
        //errorMap.put("exception", ex.getStackTrace());
        
        return errorMap;
    
    }

    @ExceptionHandler(value = Exception.class)
    //@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String,Object> handleException(Exception ex){

        Map<String,Object> errorMap = new HashMap<>();
       
        errorMap.put("cause", ex.getCause());
        errorMap.put("message", ex.getMessage());
        errorMap.put("exception", ex.getStackTrace());
        
        return errorMap;
    
    }
}
