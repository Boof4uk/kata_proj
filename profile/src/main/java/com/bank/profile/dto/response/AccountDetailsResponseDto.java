package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Account details response")
public record AccountDetailsResponseDto(
        @Schema(example = "1")
        Long id,
        @Schema(example = "1",
                description = "Id Account of the account details service")

        Long accountId,
        @Schema(description = "see ProfileResponseDto")
        ProfileResponseDto profileResponseDto) {
}
