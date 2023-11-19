package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Сущность Аудит")
public class AuditDTO {

    private Long id;

    @Schema(description = "тип сущности")
    @NotNull
    @Size(max = 40)
    private String entity_type;

    @Schema(description = "тип операции")
    @NotNull
    @Size(max = 255)
    private String operation_type;

    @Schema(description = "кто создал")
    @NotNull
    @Size(max = 255)
    private String created_by;

    @Schema(description = "кто изменил")
    @Size(max = 255)
    private String modified_by;

    @Schema(description = "когда создан",example = "2023-11-14 20:36:36")
    @NotNull
    private String created_at;

    @Schema(description = "когда изменен", example = "2023-11-14 20:36:36")
    private String modified_at;

    @Schema(description = "json, заполняется при изменении")
    private String new_entity_json;

    @Schema(description = "json, заполняется при изменении и сохранении")
    @NotNull
    private String entity_json;
}
