package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Account details request")
public record AccountDetailsRequestDto(
        @Schema(
                description = "Id Account of the account details service",
                example = "1",
                required = true
        )
        @NotBlank(message = "accountId is mandatory")
        Long accountId,
        @Schema(description = "Id Profile of the profile service",
                example = "1",
                required = true)

        @NotBlank(message = "profileId is mandatory")
        Long profileId) {
}
