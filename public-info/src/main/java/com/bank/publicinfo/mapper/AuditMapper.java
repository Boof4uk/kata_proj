package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    AuditDTO entityToDto(Audit audit);
    Audit dtoToEntity(AuditDTO auditDTO);
}
