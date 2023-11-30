package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Schema(description = "Passport request data")
public record PassportRequestDto(
        @Schema(
                description = "Series of the passport",
                example = "1234",
                required = true
        )@NotBlank(message = "Series is mandatory")
        Integer series,

        @Schema(
                description = "Number of the passport",
                example = "567890",
                required = true
        )@NotBlank(message = "Number is mandatory")
        Long number,

        @Schema(
                description = "Last name of the passport holder",
                example = "Doe",
                required = true,
                maxLength = 255
        )@NotBlank(message = "Last name is mandatory")
        String lastName,

        @Schema(
                description = "First name of the passport holder",
                example = "John",
                required = true,
                maxLength = 255
        )@NotBlank(message = "First name is mandatory")
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
        )@NotBlank(message = "gender is mandatory")
        String gender,

        @Schema(
                description = "Birth date of the passport holder",
                example = "1990-01-01",
                required = true
        )@NotBlank(message = "Birth date is mandatory")
        LocalDate birthDate,

        @Schema(
                description = "Birth place of the passport holder",
                example = "City, Country",
                required = true,
                maxLength = 480
        )@NotBlank(message = "Birth place is mandatory")
        String birthPlace,

        @Schema(
                description = "Authority that issued the passport",
                example = "Department of State",
                required = true
        )@NotBlank(message = "Issued by is mandatory")
        String issuedBy,

        @Schema(
                description = "Date of issue of the passport",
                example = "2010-01-01",
                required = true
        )@NotBlank(message = "Date of issue is mandatory")
        LocalDate dateOfIssue,

        @Schema(
                description = "Division code of the issuing authority",
                example = "123456",
                required = true
        )@NotBlank(message = "Division code is mandatory")
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
        )@NotBlank(message = "Registration ID is mandatory")
        Long registrationId
) {
}
