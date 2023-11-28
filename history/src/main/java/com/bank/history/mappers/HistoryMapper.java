package com.bank.history.mappers;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.models.History;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    HistoryDTO toDTO(History history);
    History toEntity(HistoryDTO historyDTO);
    List<HistoryDTO> toDTOList(List<History> historyList);
}
