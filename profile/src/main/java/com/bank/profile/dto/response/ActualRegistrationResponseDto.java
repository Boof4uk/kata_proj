package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Actual registration response")
public record ActualRegistrationResponseDto(
@Schema(example = "1")
        Long id,
        @Schema(example = "Russia")
        String country,
        @Schema(example = "Moscow")
        String region,
        @Schema(example = "Moscow")
        String city,
        @Schema(example = "Tverskoy")
        String district,
        @Schema(example = "Local area name")
        String locality,
        @Schema(example = "Tverskaya")
        String street,
        @Schema(example = "1")
        String houseNumber,
        @Schema(example = "B")
        String houseBlock,
        @Schema(example = "101")
        String flatNumber,
        @Schema(example = "123456")
        Long index
) {
}

