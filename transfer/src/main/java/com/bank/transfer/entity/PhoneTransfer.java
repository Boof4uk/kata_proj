package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
 * Сущность телефонного перевода
 */

@Entity
@Table(schema = "transfer", name = "phone_transfer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PhoneTransfer {

    /**
     * Идентификатор перевода
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Номер телефона
     */

    @NotNull
    @Column(name = "phone_number")
    private Long phoneNumber;

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
