package com.springlearning.io.todolist.model;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    
    private HttpStatus httpStatus;
    private String message;


}
