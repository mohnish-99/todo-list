package com.springlearning.io.todolist.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.springlearning.io.todolist.model.ErrorMessage;

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

    @ExceptionHandler(ResourceAlreadyPresentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleDuplicateDataException(ResourceAlreadyPresentException ex, WebRequest req){
        return ErrorMessage.builder()
                            .httpStatus(HttpStatus.CONFLICT)
                            .message(ex.getMessage())
                            .build();
    
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleException(Exception ex){
        return ErrorMessage.builder()
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .message(ex.getMessage())
                            .build();
    
    }
}
