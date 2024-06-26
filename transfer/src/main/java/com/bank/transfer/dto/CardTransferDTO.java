package com.bank.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Объект передачи данных для сущности CardTransfer
 * Содержит поля сущности CardTransfer
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CardTransferDTO {

    private Long id;

    @NotNull
    private Long cardNumber;

    @NotNull
    @Digits(integer = 20, fraction = 2)
    private BigDecimal amount;

    @Nullable
    private String purpose;

    @NotNull
    private Long accountDetailsId;
}
