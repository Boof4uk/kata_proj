package com.bank.authorization.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private final String entityName;
    private final Long entityId;

    public EntityNotFoundException(String entityName, Long entityId) {
        super(String.format("%s with id %d not found", entityName, entityId));
        this.entityName = entityName;
        this.entityId = entityId;
    }

}
