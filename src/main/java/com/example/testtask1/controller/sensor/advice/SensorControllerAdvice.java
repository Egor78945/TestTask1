package com.example.testtask1.controller.sensor.advice;

import com.example.testtask1.annotation.exception.handler.SensorControllerExceptionHandler;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = SensorControllerExceptionHandler.class)
public class SensorControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> wrongDataExceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
    }
}
