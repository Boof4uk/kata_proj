package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Сущность аудита
 */

@Entity
@Table(schema = "transfer", name = "audit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferAudit {

    /**
     * Идентификатор аудита
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Тип сущности
     */

    @NotNull
    @Column(name = "entity_type", length = 40)
    private String entityType;

    /**
     * Тип операции
     */

    @NotNull
    @Column(name = "operation_type")
    private String operationType;

    /**
     * Создатель
     */

    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    /**
     * Модификатор
     */

    @Nullable
    @Column(name = "modified_by")
    private String modifiedBy;

    /**
     * Дата создания
     */

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Дата изменения
     */

    @Nullable
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    /**
     * новая сущность json
     */

    @Nullable
    @Column(name = "new_entity_json")
    private String newEntityJson;

    /**
     * сущность json
     */

    @NotNull
    @Column(name = "entity_json")
    private String entityJson;

}
