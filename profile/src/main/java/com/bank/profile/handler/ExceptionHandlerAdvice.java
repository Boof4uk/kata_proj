package com.bank.profile.handler;


import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.component.ApiResponse;
import com.bank.profile.exeption.EntityNameExistsException;
import com.bank.profile.exeption.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ApiErrorResponse> httpMessageNotReadableHandler(HttpMessageNotReadableException exception) {
        return ApiResponse.buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ApiErrorResponse> entityNotFoundHandler(EntityNotFoundException exception) {
        return ApiResponse.buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(EntityNameExistsException.class)
    private ResponseEntity<ApiErrorResponse> entityNameExistsHandler(EntityNameExistsException exception) {
        return ApiResponse.buildErrorResponse(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ApiErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        return ApiResponse.buildErrorResponse(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        final List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            final String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return ApiResponse.buildErrorResponse(HttpStatus.BAD_REQUEST, errors.toString().replaceAll("^\\[|\\]$", ""));
    }
}
