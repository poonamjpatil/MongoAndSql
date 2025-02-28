package com.mongoAndSqlNew.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> handleDuplicateUserException(DuplicateUserException e)
   {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
   }

@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e)
{
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
}
}
