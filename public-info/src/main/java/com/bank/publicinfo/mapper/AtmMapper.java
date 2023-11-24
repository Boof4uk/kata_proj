package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BranchMapper.class)
public interface AtmMapper {

    AtmDTO entityToDto(Atm atm);
    Atm dtoToEntity(AtmDTO atmDTO);


}
