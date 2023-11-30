package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности PhoneTransfer
 */

@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {

    PhoneTransferDTO toDto(PhoneTransfer phoneTransfer);

    PhoneTransfer toEntity(PhoneTransferDTO phoneTransferDTO);

    List<PhoneTransferDTO> toDTOList(List<PhoneTransfer> list);

}
