package com.bank.profile.handler;

import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.component.ApiResponse;
import com.bank.profile.exception.BlogApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BlogControllerAdvice {
    @ExceptionHandler(BlogApiException.class)
    private ResponseEntity<ApiErrorResponse> httpBlogApiExceptionHandler(BlogApiException exception) {
        return ApiResponse.buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
