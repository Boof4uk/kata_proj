package com.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountAuditDTO {

    private Long id;

    @NotNull
    private String entityType;

    @NotNull
    private String operationType;

    @NotNull
    private String createdBy;

    @Nullable
    private String modifiedBy;

    @NotNull
    private String createdAt;

    @Nullable
    private String modifiedAt;

    @Nullable
    private String newEntityJson;

    @NotNull
    private String entityJson;
}
