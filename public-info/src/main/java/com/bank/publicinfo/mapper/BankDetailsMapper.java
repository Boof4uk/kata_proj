package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankDetailsMapper {
    
    BankDetailsDTO entityToDto(BankDetails bankDetails);
    BankDetails dtoToEntity(BankDetailsDTO bankDetailsDTO);
}
