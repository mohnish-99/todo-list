package com.springlearning.io.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MethodArgumentNotValidException extends RuntimeException{
    
    public MethodArgumentNotValidException(){
        super();
    }

        public MethodArgumentNotValidException(String message){
        super(message);
    }
        public MethodArgumentNotValidException(String message, Throwable cause){
        super(message,cause);
    }

       public MethodArgumentNotValidException(Throwable cause){
        super(cause);
    }
}
