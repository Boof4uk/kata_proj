package com.bank.transfer.mapper;

import com.bank.transfer.dto.TransferAuditDTO;
import com.bank.transfer.entity.TransferAudit;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности TransferAudit
 */

@Mapper(componentModel = "spring")
public interface TransferAuditMapper {

    TransferAuditDTO toDto(TransferAudit transferAudit);

    TransferAudit toEntity(TransferAuditDTO transferAuditDTO);

    List<TransferAuditDTO> toDTOList(List<TransferAudit> list);
}
