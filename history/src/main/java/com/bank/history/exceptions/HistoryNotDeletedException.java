package com.bank.history.exceptions;

public class HistoryNotDeletedException extends RuntimeException{
    public HistoryNotDeletedException(String message) {
        super(message);
    }
}
