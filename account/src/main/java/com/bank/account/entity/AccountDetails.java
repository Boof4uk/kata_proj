package com.bank.account.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (schema = "account",name = "account_details")
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "passport_id")
    private Long passportId;

    @NotNull
    @Column(name = "account_number", unique = true)
    private Long accountNumber;

    @NotNull
    @Column(name = "bank_details_id", unique = true)
    private Long bankDetailsId;

    @NotNull
    private BigDecimal money;

    @NotNull
    @Column(name = "negative_balance")
    private Boolean negativeBalance;

    @NotNull
    @Column(name = "profile_id")
    private Long profileId;

}
