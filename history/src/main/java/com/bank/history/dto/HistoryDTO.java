package com.bank.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class HistoryDTO {


    //Почему я ставлю ограничение, но при пост запросе это ограничение не срабатывает
    @Max(value = 9999, message = "Toooooo long value")
    private Long transferAuditId;

    private Long profileAuditId;

    private Long accountAuditId;

    private Long antiFraudAuditId;

    private Long publicBankInfoAuditId;

    private Long authorizationAuditId;

}
