package com.bank.publicinfo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit", schema = "public_bank_information")
@Data
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    private String entity_type;

    @NotNull
    @Size(max = 255)
    private String operation_type;

    @NotNull
    @Size(max = 255)
    private String created_by;

    @Size(max = 255)
    private String modified_by;

    @NotNull
    private LocalDateTime created_at;

    private LocalDateTime modified_at;

    private String new_entity_json;

    @NotNull
    private String entity_json;
}
