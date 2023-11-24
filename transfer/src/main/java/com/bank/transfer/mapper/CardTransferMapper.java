package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для сущности CardTransfer
 */

@Mapper(componentModel = "spring")
public interface CardTransferMapper {

    CardTransferDTO toDto(CardTransfer cardTransfer);

    CardTransfer toEntity(CardTransferDTO cardTransferDTO);

    List<CardTransferDTO> toDTOList(List<CardTransfer> list);
}
