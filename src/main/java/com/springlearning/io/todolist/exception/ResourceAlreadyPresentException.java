package com.springlearning.io.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceAlreadyPresentException extends RuntimeException{
    
    public ResourceAlreadyPresentException(){
        super();
    }

        public ResourceAlreadyPresentException(String message){
        super(message);
    }
        public ResourceAlreadyPresentException(String message, Throwable cause){
        super(message,cause);
    }

       public ResourceAlreadyPresentException(Throwable cause){
        super(cause);
    }
}
