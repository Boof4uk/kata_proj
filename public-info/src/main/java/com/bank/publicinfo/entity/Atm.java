package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "atm", schema = "public_bank_information")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Atm {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "address")
    private String address;

    @Column(name = "start_of_work")
    private LocalTime start_of_work;

    @Column(name = "end_of_work")
    private LocalTime end_of_work;

    @NotNull
    @Column(name = "all_hours")
    private Boolean all_hours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", nullable = true)
    private Branch branch;
}
