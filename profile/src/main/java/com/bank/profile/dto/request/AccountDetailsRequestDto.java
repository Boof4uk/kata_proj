package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "Account details request")
@NotNull(message = "accountDetailsRequestDto is mandatory")
public record AccountDetailsRequestDto(
        @Schema(
                description = "Id Account of the account details service",
                example = "1",
                required = true
        )
        @NotNull(message = "accountId is mandatory")
        Long accountId,
        @Schema(description = "Id Profile of the profile service",
                example = "1",
                required = true)

        @NotNull(message = "profileId is mandatory")
        Long profileId) {
}
