package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Passport request data")
public record PassportRequestDto(
        @Schema(
                description = "Series of the passport",
                example = "1234",
                required = true
        )
        Integer series,

        @Schema(
                description = "Number of the passport",
                example = "567890",
                required = true
        )
        Long number,

        @Schema(
                description = "Last name of the passport holder",
                example = "Doe",
                required = true,
                maxLength = 255
        )
        String lastName,

        @Schema(
                description = "First name of the passport holder",
                example = "John",
                required = true,
                maxLength = 255
        )
        String firstName,

        @Schema(
                description = "Middle name of the passport holder",
                example = "E",
                maxLength = 255
        )
        String middleName,

        @Schema(
                description = "Gender of the passport holder",
                example = "M",
                required = true,
                maxLength = 3
        )
        String gender,

        @Schema(
                description = "Birth date of the passport holder",
                example = "1990-01-01",
                required = true
        )
        LocalDate birthDate,

        @Schema(
                description = "Birth place of the passport holder",
                example = "City, Country",
                required = true,
                maxLength = 480
        )
        String birthPlace,

        @Schema(
                description = "Authority that issued the passport",
                example = "Department of State",
                required = true
        )
        String issuedBy,

        @Schema(
                description = "Date of issue of the passport",
                example = "2010-01-01",
                required = true
        )
        LocalDate dateOfIssue,

        @Schema(
                description = "Division code of the issuing authority",
                example = "123456",
                required = true
        )
        Integer divisionCode,

        @Schema(
                description = "Expiration date of the passport",
                example = "2030-01-01"
        )
        LocalDate expirationDate,

        @Schema(
                description = "ID of the registration associated with the passport",
                example = "1",
                required = true
        )
        Long registrationId
) {
}
