package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности SuspiciousCardTransfer
 */
@Mapper(componentModel = "spring")
public interface SuspiciousCardTransferMapper {

    SuspiciousCardTransferDTO toDto(SuspiciousCardTransfer suspiciousCardTransfers);

    SuspiciousCardTransfer toEntity(SuspiciousCardTransferDTO suspiciousCardTransferDTO);

    List<SuspiciousCardTransferDTO> toDtoList(List<SuspiciousCardTransfer> suspiciousCardTransferList);

}
