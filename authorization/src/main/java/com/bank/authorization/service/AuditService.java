package com.bank.authorization.service;

import com.bank.authorization.dto.AuditDto;

import java.util.List;

public interface AuditService {

    AuditDto add(AuditDto auditDto);

    List<AuditDto> getAll();

    AuditDto update(AuditDto auditDto);

    void deleteById(Long id);

    AuditDto getById(Long id);

}
