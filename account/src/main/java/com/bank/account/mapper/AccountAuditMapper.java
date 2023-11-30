package com.bank.account.mapper;

import com.bank.account.dto.AccountAuditDTO;
import com.bank.account.entity.AccountAudit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountAuditMapper {

    AccountAuditDTO toDTO(AccountAudit accountAudit);

    AccountAudit toEntity(AccountAuditDTO accountAuditDTO);

    List<AccountAuditDTO> toDTOList(List<AccountAudit>list);
}
