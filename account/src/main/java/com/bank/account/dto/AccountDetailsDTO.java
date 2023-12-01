package com.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDetailsDTO {

    private Long id;

    @NotNull
    private Long passportId;

    @NotNull
    private Long accountNumber;

    @NotNull
    private Long bankDetailsId;

    @NotNull
    private BigDecimal money;

    @NotNull
    private Boolean negativeBalance;

    @NotNull
    private Long profileId;
}
