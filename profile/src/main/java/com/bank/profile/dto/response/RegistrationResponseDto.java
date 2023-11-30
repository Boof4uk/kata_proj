package com.bank.profile.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Registration response")
public record RegistrationResponseDto(
        @Schema(example = "1")
        Long id,
        @Schema(example = "Russia")
        String country,
        @Schema(example = "Moscow Region")
        String region,
        @Schema(example = "Moscow")
        String city,
        @Schema(example = "Central")
        String district,
        @Schema(example = "Moscow")
        String locality,
        @Schema(example = "Tverskaya")
        String street,
        @Schema(example = "10")
        String houseNumber,
        @Schema(example = "A")
        String houseBlock,
        @Schema(example = "101")
        String flatNumber,
        @Schema(example = "123456")
        Long index
) {
}
