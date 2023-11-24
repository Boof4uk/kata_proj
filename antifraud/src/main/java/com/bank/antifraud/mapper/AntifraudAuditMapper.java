package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.entity.AntifraudAudit;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности AntifraudAudit
 */
@Mapper(componentModel = "spring")
public interface AntifraudAuditMapper {
    AntifraudAuditDTO toDTO(AntifraudAudit antifraudAudit);

    AntifraudAudit toEntity(AntifraudAuditDTO antifraudAuditDTO);

    List<AntifraudAuditDTO> toDtoList(List<AntifraudAudit> antifraudAuditList);
}
