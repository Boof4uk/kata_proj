package com.bank.antifraud.dto;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Объект передачи данных для сущности SuspiciousAccountTransfers
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SuspiciousAccountTransfersDTO {

    private Long id;

    @NotNull
    private Integer accountTransferId;

    @NotNull
    private boolean isBlocked;

    @NotNull
    private boolean isSuspicious;
    @Nullable
    private String blockedReason;

    @NotNull
    private String suspiciousReason;

}
