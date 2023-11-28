package com.bank.antifraud.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * Объект передачи данных для сущности SuspiciousPhoneTransfers
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SuspiciousPhoneTransfersDTO {

    private Long id;

    @NotNull
    private Long phoneTransferId;

    @NotNull
    private boolean isBlocked;

    @NotNull
    private boolean isSuspicious;

    @Nullable
    private String blockedReason;

    @NotNull
    private String suspiciousReason;

}
