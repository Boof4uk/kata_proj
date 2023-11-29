package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Schema(description = "Сущность лицензия")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LicenseDTO {

    private Long id;

    @Schema(description = "Фотография лицензии в виде массива байт")
    @NotNull
    private byte[] photo;

    @Schema(description = "Реквизиты банка, связанные с лицензией")
    @NotNull
    private BankDetailsDTO bankDetails;
}
