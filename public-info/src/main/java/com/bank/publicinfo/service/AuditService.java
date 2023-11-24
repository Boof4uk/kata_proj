package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AuditDTO;

import java.util.List;
import java.util.Optional;

public interface AuditService {


    public List<AuditDTO> getAll();
    public AuditDTO find(Long id);

    public void save(AuditDTO audit);

    public void update(AuditDTO audit);

    public void delete(Long id);

}
