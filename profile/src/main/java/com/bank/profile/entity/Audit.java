package com.bank.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit", schema = "profile")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_type", nullable = false, length = 40)
    private String entityType;

    @Column(name = "operation_type", nullable = false, length = 255)
    private String operationType;

    @Column(name = "created_by", nullable = false, length = 255)
    private String createdBy;

    @Column(name = "modified_by", length = 255)
    private String modifiedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "new_entity_json", columnDefinition = "TEXT")
    private String newEntityJson;

    @Column(name = "entity_json", nullable = false, columnDefinition = "TEXT")
    private String entityJson;
    @PrePersist
    @PreUpdate
    public void roundDateTime() {
        if (createdAt != null) {
           this.createdAt = this.createdAt.truncatedTo(ChronoUnit.SECONDS);
        }
        if (modifiedAt != null) {
            this.modifiedAt = this.modifiedAt.truncatedTo(ChronoUnit.SECONDS);
        }
    }
}
