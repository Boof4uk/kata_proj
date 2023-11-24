package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
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

@Entity
@Table(name = "bank_details", schema = "public_bank_information")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "bik", unique = true)
    private Long bik;

    @NotNull
    @Column(name = "inn", unique = true)
    private Long inn;

    @NotNull
    @Column(name = "kpp",unique = true)
    private Long kpp;

    @NotNull
    @Column(name = "cor_account", unique = true)
    private Integer cor_account;

    @NotNull
    private String city;

    @NotNull
    private String joint_stock_company;

    @NotNull
    private String name;
}
