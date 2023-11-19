package com.bank.authorization.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(schema = "\"authorization\"", name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column(name = "entity_type", length = 40)
    private String entityType;

    @NotNull
    @Column(name = "operation_type")
    private String operationType;

    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    @NotNull
    @Column(name = "modified_by")
    private String modifiedBy;

    @Nullable
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Nullable
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @NotNull
    @Column(name = "new_entity_json")
    private String newEntityJson;

    @NotNull
    @Column(name = "entity_json")
    private String entityJson;
}
