package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDTO;

import java.util.List;

public interface BankDetailsService {


    public List<BankDetailsDTO> getAll();
    public BankDetailsDTO find(Long id);

    public void save(BankDetailsDTO bankDetails);

    public void update(BankDetailsDTO bankDetails);

    public void delete(Long id);

}
