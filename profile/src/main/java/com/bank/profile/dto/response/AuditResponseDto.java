package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Audit response")
public record AuditResponseDto(
        @Schema(example = "1")

        Long id,
        @Schema(example = "User")
        String entityType,
        @Schema(example = "Create")
        String operationType,
        @Schema(example = "admin")
        String createdBy,
        @Schema(example = "editor")
        String modifiedBy,
        @Schema(example = "2023-10-31T12:00:00")
        String createdAt,
        @Schema(example = "2023-10-31T15:00:00")
        String modifiedAt,
        @Schema(example = "{\"name\":\"New User\",\"role\":\"admin\"}")
        String newEntityJson,
        @Schema(example = "{\"name\":\"Old User\",\"role\":\"user\"}")
        String entityJson) {
}
