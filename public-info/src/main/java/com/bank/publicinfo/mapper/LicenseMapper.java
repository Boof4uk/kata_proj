package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.License;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BankDetailsMapper.class)
public interface LicenseMapper {

    LicenseDTO entityToDto(License license);
    License dtoToEntity(LicenseDTO licenseDTO);
}
