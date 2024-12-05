package com.example.testtask1.controller.authentication.advice;

import com.example.testtask1.annotation.exception.handler.AuthenticationControllerExceptionHandler;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = AuthenticationControllerExceptionHandler.class)
public class AuthenticationServiceControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> wrongDataExceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(401));
    }
}
