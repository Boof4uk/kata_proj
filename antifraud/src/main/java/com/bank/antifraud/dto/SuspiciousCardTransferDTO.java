package com.bank.antifraud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * Объект передачи данных для сущности SuspiciousCardTransfer
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SuspiciousCardTransferDTO {

    private Long id;

    @NotNull
    private Integer cardTransferId;

    @NotNull
    private boolean isBlocked;

    @NotNull
    private boolean isSuspicious;

    @Nullable
    private String blockedReason;

    @NotNull
    private String suspiciousReason;
}
