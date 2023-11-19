package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
@Schema(description = "Сущность реквизиты банка")
public class BankDetailsDTO {

    private Long id;

    @NotNull
    @Schema(description = "БИК")
    private Long bik;

    @NotNull
    @Schema(description = "ИНН")
    private Long inn;

    @NotNull
    @Schema(description = "КПП")
    private Long kpp;

    @NotNull
    @Schema(description = "Корреспондентский счет")
    private Integer cor_account;

    @NotNull
    @Schema(description = "Город, в котором зарегистрирован юридический адрес банка")
    private String city;

    @NotNull
    @Schema(description = "Акционерное общество")
    private String joint_stock_company;

    @NotNull
    @Schema(description = "Имя банка")
    private String name;
}

