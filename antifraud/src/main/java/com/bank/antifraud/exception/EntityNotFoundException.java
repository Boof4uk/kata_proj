package com.bank.antifraud.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String name;
    private final Long value;

    public EntityNotFoundException(String name, Long value) {
        super(String.format("%s not found with id : %d", name, value));
        this.name = name;
        this.value = value;
    }
}
