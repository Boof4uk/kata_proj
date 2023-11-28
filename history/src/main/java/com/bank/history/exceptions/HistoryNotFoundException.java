package com.bank.history.exceptions;

public class HistoryNotFoundException extends RuntimeException{
    public HistoryNotFoundException(String message) {
        super(message);
    }
}
