package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Registration request data")
public record RegistrationRequestDto(
        @Schema(
                description = "Country of registration",
                example = "Russia",
                required = true,
                maxLength = 166
        )
        String country,

        @Schema(
                description = "Region of registration",
                example = "Moscow Region",
                maxLength = 160
        )
        String region,

        @Schema(
                description = "City of registration",
                example = "Moscow",
                maxLength = 160
        )
        String city,

        @Schema(
                description = "District of registration",
                example = "Central",
                maxLength = 160
        )
        String district,

        @Schema(
                description = "Locality of registration",
                example = "Moscow",
                maxLength = 230
        )
        String locality,

        @Schema(
                description = "Street of registration",
                example = "Tverskaya",
                maxLength = 230
        )
        String street,

        @Schema(
                description = "House number of registration",
                example = "10",
                maxLength = 20
        )
        String houseNumber,

        @Schema(
                description = "House block of registration",
                example = "A",
                maxLength = 20
        )
        String houseBlock,

        @Schema(
                description = "Flat number of registration",
                example = "101",
                maxLength = 40
        )
        String flatNumber,

        @Schema(
                description = "Index (postal code) of registration",
                example = "123456",
                required = true
        )
        Long index
) {
}