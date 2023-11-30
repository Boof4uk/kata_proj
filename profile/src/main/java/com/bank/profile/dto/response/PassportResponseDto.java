package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Passport response")
public record PassportResponseDto(
        @Schema(example = "1")
        Long id,
        @Schema(description = "Series of the passport",
                example = "1234")
        Integer series,
        @Schema(description = "Number of the passport",
                example = "567890")
        Long number,
        @Schema(example = "Doe")
        String lastName,
        @Schema(example = "John")
        String firstName,
        @Schema(example = "E")
        String middleName,
        @Schema(example = "M or W")
        String gender,
        @Schema(example = "1990-01-01")
        String birthDate,
        @Schema(example = "City, Country")
        String birthPlace,
        @Schema(example = "Department of State")
        String issuedBy,
        @Schema(example = "2010-01-01")
        String dateOfIssue,
        @Schema(example = "123456")
        Integer divisionCode,
        @Schema(example = "2020-01-01")
        String expirationDate,
        @Schema(description = "see RegistrationResponseDto")
        RegistrationResponseDto registration
) {
}