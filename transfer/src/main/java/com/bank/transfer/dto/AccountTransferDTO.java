package com.bank.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Объект передачи данных для сущности AccountTransfer
 * Содержит поля сущности AccountTransfer
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountTransferDTO {

    private Long id;

    @NotNull
    private Long accountNumber;

    @NotNull
    @Digits(integer = 20, fraction = 2)
    private BigDecimal amount;

    @Nullable
    private String purpose;

    @NotNull
    private Long accountDetailsId;
}
