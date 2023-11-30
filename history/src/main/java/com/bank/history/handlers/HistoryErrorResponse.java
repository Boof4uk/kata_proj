package com.bank.history.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HistoryErrorResponse {
    private String message;
    private Long timestamp;
}
