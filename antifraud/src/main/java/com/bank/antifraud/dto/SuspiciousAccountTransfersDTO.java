package com.bank.antifraud.dto;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Объект передачи данных для сущности SuspiciousAccountTransfers
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SuspiciousAccountTransfersDTO {

    private Long id;

    @NotNull
    private Long accountTransferId;

    @NotNull
    private boolean isBlocked;

    @NotNull
    private boolean isSuspicious;
    @Nullable
    private String blockedReason;

    @NotNull
    private String suspiciousReason;

}
