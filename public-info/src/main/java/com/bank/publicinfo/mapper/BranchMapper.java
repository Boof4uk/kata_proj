package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
        BranchDTO entityToDto(Branch Branch);
        Branch dtoToEntity(BranchDTO BranchDTO);
}
