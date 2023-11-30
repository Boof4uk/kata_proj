package com.bank.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditDto {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String entityType;

    @NotNull
    private String operationType;

    @NotNull
    private String createdBy;

    @NotNull
    private String modifiedBy;

    @Nullable
    private LocalDateTime createdAt;

    @Nullable
    private LocalDateTime modifiedAt;

    @NotNull
    private String newEntityJson;

    @NotNull
    private String entityJson;
}
