package com.bank.profile.exception;

import lombok.Getter;

@Getter
public class EntityNameExistsException extends RuntimeException {
    public EntityNameExistsException(String message) {
        super(message);
    }
}
