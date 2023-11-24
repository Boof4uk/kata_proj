package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDTO;
import com.bank.antifraud.entity.SuspiciousPhoneTransfers;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности SuspiciousPhoneTransfers
 */
@Mapper(componentModel = "spring")
public interface SuspiciousPhoneTransfersMapper {

    SuspiciousPhoneTransfersDTO toDto(SuspiciousPhoneTransfers suspiciousPhoneTransfers);

    SuspiciousPhoneTransfers toEntity(SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO);

    List<SuspiciousPhoneTransfersDTO> toDtoList(List<SuspiciousPhoneTransfers> suspiciousPhoneTransfersList);

}
