package com.bank.profile.service;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;

import java.util.List;

public interface AuditService {
    AuditResponseDto create(AuditRequestDto auditRequestDto);
    AuditResponseDto getById(Long id);
    AuditResponseDto update(Long id, AuditRequestDto auditRequestDto);
    void delete(Long id);
    List<AuditResponseDto> getAll();
}
