package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDTO;

import java.util.List;

public interface LicenseService {

    public List<LicenseDTO> getAll();
    public LicenseDTO find(Long id);

    public void save(LicenseDTO license);

    public void update(LicenseDTO license);

    public void delete(Long id);
}
