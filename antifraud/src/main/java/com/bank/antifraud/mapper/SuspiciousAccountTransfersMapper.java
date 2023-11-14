package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.entity.SuspiciousAccountTransfers;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности SuspiciousAccountTransfers
 */
@Mapper(componentModel = "spring")
public interface SuspiciousAccountTransfersMapper {
    SuspiciousAccountTransfersDTO toDto(SuspiciousAccountTransfers suspiciousAccountTransfers);

    SuspiciousAccountTransfers toEntity(SuspiciousAccountTransfersDTO suspiciousAccountTransfersDTO);

    List<SuspiciousAccountTransfersDTO> toDtoList(List<SuspiciousAccountTransfers> suspiciousAccountTransfersList);

}
