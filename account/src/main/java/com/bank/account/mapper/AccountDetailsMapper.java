package com.bank.account.mapper;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {

    AccountDetailsDTO toDTO(AccountDetails accountDetails);

    AccountDetails toEntity(AccountDetailsDTO accountDetailsDTO);

    List<AccountDetailsDTO> toDTOList(List<AccountDetails>list);
}
