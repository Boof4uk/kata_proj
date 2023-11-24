package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDTO;

import java.util.List;

public interface BranchService {


    public List<BranchDTO> getAll();
    public BranchDTO find(Long id);

    public void save(BranchDTO branch);

    public void update(BranchDTO branch);

    public void delete(Long id);
}
