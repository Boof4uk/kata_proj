package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Сущность лицензия")
public class LicenseDTO {

    private Long id;

    @Schema(description = "Фотография лицензии в виде массива байт")
    private Byte[] photo;

    @Schema(description = "Реквизиты банка, связанные с лицензией")
    private BankDetails bankDetails;
}
