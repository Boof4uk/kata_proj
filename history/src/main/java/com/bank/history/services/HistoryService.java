package com.bank.history.services;

import com.bank.history.dto.HistoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoryService {

    HistoryDTO save(HistoryDTO historyDTO);
    List<HistoryDTO> getAll();
    HistoryDTO getById(Long id);
    HistoryDTO update(HistoryDTO historyDTO, Long id);
    void delete(Long id);
}
