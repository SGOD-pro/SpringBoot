package com.example.RestApi.exception;

import com.example.RestApi.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice // SpringBoot automatically invoke it when any exception occurs in this application
public class GlobalException {
    @ExceptionHandler(CategoryExists.class)
    public ResponseEntity<ExceptionDTO> handelAlreadyExists(CategoryExists e, WebRequest webRequest) {
        ExceptionDTO exc = new ExceptionDTO(webRequest.getDescription(false), HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exc);
    }

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<String> handelNotExists(CategoryNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    //global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> globalException(Exception e, WebRequest webRequest) {
        ExceptionDTO exc = new ExceptionDTO(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exc);
    }
}
