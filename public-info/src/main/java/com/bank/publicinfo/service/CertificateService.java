package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDTO;

import java.util.List;

public interface CertificateService {
    public List<CertificateDTO> getAll();
    public CertificateDTO find(Long id);

    public void save(CertificateDTO certificate);

    public void update(CertificateDTO certificate);

    public void delete(Long id);
}
