package com.bank.authorization.component;


import org.springframework.http.HttpStatus;


public record ApiErrorResponse(HttpStatus httpStatus, String message) {

}
