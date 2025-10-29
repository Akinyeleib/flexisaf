package com.flexisaf.tasks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedValidationException.class)
    public Map<String, String> handleFailedValidationException(FailedValidationException f) {
        Map<String, String> data = new HashMap<>();
        data.put("message", f.getMessage());
        return data;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleFailedValidationExceptionAutomatic(MethodArgumentNotValidException m) {
        Map<String, String> data = new HashMap<>();
        m.getBindingResult().getFieldErrors().forEach(error -> {
            data.put(error.getField(), error.getDefaultMessage());
        });
        return data;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    public Map<String, String> handlePersonNotFoundException(PersonNotFoundException p) {
        Map<String, String> data = new HashMap<>();
        data.put("message", p.getMessage());
        return data;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGenericException(Exception p) {
        Map<String, String> data = new HashMap<>();
        data.put("message", p.getMessage());
        return data;
    }

}
