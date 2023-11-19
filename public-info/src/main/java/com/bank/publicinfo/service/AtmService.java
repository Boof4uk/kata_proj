package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDTO;

import java.util.List;

public interface AtmService {


    public List<AtmDTO> getAll();
    public AtmDTO find(Long id);

    public void save(AtmDTO atm);

    public void update(AtmDTO atm);

    public void delete(Long id);

}
