package com.bank.history.handlers;

import com.bank.history.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HistoryExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<HistoryErrorResponse> handleHistoryNotFoundException(HistoryNotFoundException e) {
        HistoryErrorResponse historyErrorResponse = new HistoryErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(historyErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HistoryErrorResponse> handleHistoryNotCreatedException(HistoryNotCreatedException e) {
        HistoryErrorResponse historyErrorResponse = new HistoryErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(historyErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<HistoryErrorResponse> handleHistoryNotUpdatedException(HistoryNotUpdatedException e) {
        HistoryErrorResponse historyErrorResponse = new HistoryErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(historyErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<HistoryErrorResponse> handleNotDeletedHistoryException(HistoryNotDeletedException e) {
        HistoryErrorResponse historyErrorResponse = new HistoryErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(historyErrorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<HistoryErrorResponse> handeAllHistoriesException(AllHistoriesException e) {
        HistoryErrorResponse historyErrorResponse = new HistoryErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(historyErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
