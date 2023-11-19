package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Certificate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BankDetailsMapper.class)
public interface CertificateMapper {

    CertificateDTO entityToDto(Certificate certificate);
    Certificate dtoToEntity(CertificateDTO certificateDTO);
}
