package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Сущность Аудит")
public class AuditDTO {

    private Long id;

    @Schema(description = "тип сущности")
    @Size(max = 40)
    @NotNull
    private String entityType;

    @Schema(description = "тип операции")
    @NotNull
    @Size(max = 255)
    private String operationType;

    @Schema(description = "кто создал")
    @NotNull
    @Size(max = 255)
    private String createdBy;

    @Schema(description = "кто изменил")
    @Size(max = 255)
    private String modifiedBy;

    @Schema(description = "когда создан",example = "2023-11-14T20:36:36")
    @NotNull
    private String createdAt;

    @Schema(description = "когда изменен", example = "2023-11-14T20:36:36")
    private String modifiedAt;

    @Schema(description = "json, заполняется при изменении")
    private String newEntityJson;

    @Schema(description = "json, заполняется при изменении и сохранении")
    @NotNull
    private String entityJson;
}
