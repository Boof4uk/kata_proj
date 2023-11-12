package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Сущность перевода на карту
 */

@Entity
@Table(schema = "transfer", name = "card_transfer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardTransfer {

    /**
     * Идентификатор перевода
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Номер карты
     */

    @NotNull
    @Column(name = "card_number", unique = true)
    private Long cardNumber;

    /**
     * Сумма
     */

    @NotNull
    @Digits(integer = 20, fraction = 2)
    private BigDecimal amount;

    /**
     * Цель перевода
     */

    @Nullable
    private String purpose;

    /**
     * Идентификатор данных учетной записи
     */

    @NotNull
    @Column(name = "account_details_id")
    private Long accountDetailsId;
}
