package com.bank.antifraud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Объект передачи данных для сущности AntifraudAudit
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AntifraudAuditDTO {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String entityType;

    @NotNull
    private String operationType;

    @NotNull
    private String createdBy;

    @Nullable
    private String modifiedBy;

    @NotNull
    private LocalDateTime createdAt;

    @Nullable
    private LocalDateTime modifiedAt;

    @Nullable
    private String newEntityJson;

    @NotNull
    private String entityJson;
}