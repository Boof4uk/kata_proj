package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Сущность сертификат")
public class CertificateDTO {

    private Long id;

    @Schema(description = "Фотография лицензии в виде массива байт")
    @NotNull
    private byte[] photo;


    @Schema(description = "Реквизиты банка, связанные с сертификатом")
    @NotNull
    private BankDetailsDTO bankDetails;
}
