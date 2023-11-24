package com.bank.antifraud.entity;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Сущность подозрительных переводов по номеру телефона
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "anti_fraud", name = "suspicious_phone_transfers")
public class SuspiciousPhoneTransfers {
    /**
     * Идентификатор сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Tехнический идентификатор на перевод по номеру телефона
     */
    @NotNull
    @Column(name = "phone_transfer_id", unique = true)
    private Integer phoneTransferId;
    /**
     * Заблокировано ли
     */
    @NotNull
    @Column(name = "is_blocked")
    private boolean isBlocked;
    /**
     * Подозрительный ли перевод
     */
    @NotNull
    @Column(name = "is_suspicious")
    private boolean isSuspicious;
    /**
     * Причина блокировки
     */
    @Nullable
    @Column(name = "blocked_reason")
    private String blockedReason;
    /**
     * Причина почему перевод попал в антифрод
     */
    @NotNull
    @Column(name = "suspicious_reason")
    private String suspiciousReason;
}
