package com.bank.authorization.service.impl;

import com.bank.authorization.repository.AuditRepository;
import com.bank.authorization.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditServiceImp implements AuditService {

    private final AuditRepository auditRepository;

}
