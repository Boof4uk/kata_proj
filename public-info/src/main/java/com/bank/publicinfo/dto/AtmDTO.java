package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.Branch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
@Data
@Schema(description = "Сущность банкомат")
public class AtmDTO {

    private Long id;

    @Schema(description = "адрес")
    @NotNull
    private String address;

    @Schema(description = "старт работы")
    private LocalTime start_of_work;

    @Schema(description = "конец работы")
    private LocalTime end_of_work;

    @Schema(description = "работает круглосуточно")
    @NotNull
    private Boolean all_hours;

    private BranchDTO branch;
}
