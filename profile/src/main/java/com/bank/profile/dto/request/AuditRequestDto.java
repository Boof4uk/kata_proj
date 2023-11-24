package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Audit request data")
public record AuditRequestDto(
        @Schema(
                description = "Type of the audited entity",
                example = "User",
                required = true,
                maxLength = 40
        )
        String entityType,

        @Schema(
                description = "Type of operation performed",
                example = "Create",
                required = true,
                maxLength = 255
        )
        String operationType,

        @Schema(
                description = "Identifier of the user who created the entity",
                example = "admin",
                required = true,
                maxLength = 255
        )
        String createdBy,

        @Schema(
                description = "Identifier of the user who last modified the entity",
                example = "editor",
                maxLength = 255
        )
        String modifiedBy,

        @Schema(
                description = "Timestamp of entity creation",
                example = "2023-10-31T12:00:00",
                required = true
        )
        LocalDateTime createdAt,

        @Schema(
                description = "Timestamp of last modification of the entity",
                example = "2023-10-31T15:00:00"
        )
        LocalDateTime modifiedAt,

        @Schema(
                description = "JSON representation of the new entity state",
                example = "{\"name\":\"New User\",\"role\":\"admin\"}"
        )
        String newEntityJson,

        @Schema(
                description = "JSON representation of the original entity state",
                example = "{\"name\":\"Old User\",\"role\":\"user\"}",
                required = true
        )
        String entityJson
) {
}

