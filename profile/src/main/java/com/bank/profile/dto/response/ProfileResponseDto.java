package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Profile response")
public record ProfileResponseDto(
        @Schema(example = "1")
        Long id,
        @Schema(example = "1234567890")
        Long phoneNumber,
        @Schema(example = "user@exampl.com")
        String email,
        @Schema(example = "John Doe")
        String nameOnCard,
        @Schema(example = "123456789012")
        Long inn,
        @Schema(example = "12345678901")
        Long snils,

        @Schema(description = "see PassportResponseDto")
        PassportResponseDto passport,
        @Schema(description = "see ActualRegistrationResponseDto")
        ActualRegistrationResponseDto actualRegistration) {
}
