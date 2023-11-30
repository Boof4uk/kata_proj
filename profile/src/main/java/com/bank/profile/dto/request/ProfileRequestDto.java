package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Profile request data")
public record ProfileRequestDto(
        @Schema(
                description = "Phone number of the profile",
                example = "1234567890",
                required = true
        )@NotBlank(message = "Phone number is mandatory")
        Long phoneNumber,

        @Schema(
                description = "Email address of the profile",
                example = "user@example.com",
                maxLength = 264
        )
        String email,

        @Schema(
                description = "Name on the card associated with the profile",
                example = "John Doe",
                maxLength = 370
        )
        String nameOnCard,

        @Schema(
                description = "INN (Individual Taxpayer Number) of the profile",
                example = "123456789012"
        )
        Long inn,

        @Schema(
                description = "SNILS (Russian pension insurance number) of the profile",
                example = "12345678901"
        )
        Long snils,

        @Schema(
                description = "ID of the passport associated with the profile",
                example = "1",
                required = true
        )@NotBlank(message = "Passport ID is mandatory")
        Long passportId,

        @Schema(
                description = "ID of the actual registration associated with the profile",
                example = "2",
                required = true
        )@NotBlank(message = "Actual registration ID is mandatory")
        Long actualRegistrationId
) {
}