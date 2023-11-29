package com.bank.antifraud.entity;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность подозрительных переводов по номеру счета
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "anti_fraud", name = "suspicious_account_transfers")
public class SuspiciousAccountTransfers {
    /**
     * Идентификатор сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Tехнический идентификатор на перевод по номеру cчёта
     */
    @Column(name = "account_transfer_id", unique = true)
    private Long accountTransferId;
    /**
     * Заблокировано ли
     */
    @Column(name = "is_blocked")
    private boolean isBlocked;
    /**
     * Подозрительный ли перевод
     */
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
    @Column(name = "suspicious_reason")
    private String suspiciousReason;
}
