package com.redis.redis_practice.excHandeling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandling {
    @ExceptionHandler(UserPrincipalNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found. Please check your request details.");
    }
}
