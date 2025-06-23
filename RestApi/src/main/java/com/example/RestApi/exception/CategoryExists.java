package com.example.RestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CategoryExists extends RuntimeException{
    public CategoryExists(String message){
        super(message);
    }
}
