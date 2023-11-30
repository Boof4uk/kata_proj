package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Schema(description = "Сущность отделение банка")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BranchDTO {

    private Long id;

    @NotNull
    @Schema(description = "Адрес отделения", example = "123 Main Street")
    private String address;

    @NotNull
    @Schema(description = "Номер телефона отделения", example = "15551234567")
    private Long phone_number;

    @NotNull
    @Schema(description = "Город, в котором находится отделение", example = "New York")
    private String city;

    @NotNull
    @Schema(description = "Начало работы отделения", example = "08:00:00")
    private LocalTime start_of_work;

    @NotNull
    @Schema(description = "Конец работы отделения", example = "18:00:00")
    private LocalTime end_of_work;
}

