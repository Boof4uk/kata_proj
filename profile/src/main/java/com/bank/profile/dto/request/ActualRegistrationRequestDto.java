package com.bank.profile.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Actual registration request")
public record ActualRegistrationRequestDto(
        @Schema(
                description = "Country of the registration",
                example = "Russia",
                required = true,
                maxLength = 40
        )
        @NotBlank(message = "Country is mandatory")
        String country,

        @Schema(
                description = "Region of the registration",
                example = "Moscow",
                maxLength = 160
        )
        String region,

        @Schema(
                description = "City of the registration",
                example = "Moscow",
                maxLength = 160
        )
        String city,

        @Schema(
                description = "District of the registration",
                example = "Tverskoy",
                maxLength = 160
        )
        String district,

        @Schema(
                description = "Locality of the registration",
                example = "Local area name",
                maxLength = 230
        )
        String locality,

        @Schema(
                description = "Street of the registration",
                example = "Tverskaya",
                maxLength = 230
        )
        String street,

        @Schema(
                description = "House number of the registration",
                example = "1",
                maxLength = 20
        )
        String houseNumber,

        @Schema(
                description = "House block of the registration",
                example = "B",
                maxLength = 20
        )
        String houseBlock,

        @Schema(
                description = "Flat number of the registration",
                example = "101",
                maxLength = 40
        )
        String flatNumber,

        @Schema(
                description = "Index of the registration",
                example = "123456",
                required = true
        )
        Long index
) {
}
