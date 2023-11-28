package com.bank.antifraud.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Сущность аудита
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "anti_fraud", name = "audit")
public class AntifraudAudit {
    /**
     * Идентификатор аудита
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Tип сущности
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
     * Кто создал
     */
    @NotNull
    @Column(name = "created_by")
    private String createdBy;
    /**
     * Кто изменил
     */
    @Nullable
    @Column(name = "modified_by")
    private String modifiedBy;
    /**
     * Когда создан
     */
    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    /**
     * Когда изменен
     */
    @Nullable
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    /**
     * Json, заполняется при изменении
     */
    @Nullable
    @Column(name = "new_entity_json")
    private String newEntityJson;
    /**
     * Json, заполняется при изменении и при сохранении
     */
    @NotNull
    @Column(name = "entity_json")
    private String entityJson;
}
