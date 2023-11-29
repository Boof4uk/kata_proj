package com.bank.history.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(schema = "history", name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long transferAuditId;

    @Column
    private Long profileAuditId;

    @Column
    private Long accountAuditId;

    @Column
    private Long antiFraudAuditId;

    @Column
    private Long publicBankInfoAuditId;

    @Column
    private Long authorizationAuditId;





}
