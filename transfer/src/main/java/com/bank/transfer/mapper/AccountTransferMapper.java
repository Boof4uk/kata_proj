package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности AccountTransfer
 */

@Mapper(componentModel = "spring")
public interface AccountTransferMapper {

    AccountTransferDTO toDto(AccountTransfer accountTransfer);

    AccountTransfer toEntity(AccountTransferDTO accountTransferDTO);

    List<AccountTransferDTO> toDTOList(List<AccountTransfer> list);

}
