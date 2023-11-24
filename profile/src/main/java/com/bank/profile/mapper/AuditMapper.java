package com.bank.profile.mapper;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.entity.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    AuditResponseDto toDto(Audit audit);
    @Mapping(target = "id", ignore = true)
    Audit toEntity(AuditRequestDto auditRequestDto);
    List<AuditResponseDto> toDTOList(List<Audit> list);
}
