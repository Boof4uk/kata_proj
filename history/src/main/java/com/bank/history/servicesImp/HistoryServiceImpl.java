package com.bank.history.servicesImp;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.exceptions.*;
import com.bank.history.mappers.HistoryMapper;
import com.bank.history.models.History;
import com.bank.history.repositories.HistoryRepository;
import com.bank.history.services.HistoryService;
import liquibase.pro.packaged.E;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;


    @Override
    @Transactional
    public HistoryDTO save(HistoryDTO historyDTO) {
        log.info("Saving new history...");
        try {
            History history = historyMapper.toEntity(historyDTO);
            historyRepository.save(history);
            log.info("Success!!");
            return historyMapper.toDTO(history);
        } catch(Exception e) {
            log.error("New history wasn't created");
            throw new HistoryNotCreatedException("History not created");
        }
    }

    @Override
    @Transactional
    public List<HistoryDTO> getAll() {
        log.info("Getting all histories...");
        try {
            log.info("Success!!");
            return historyMapper.toDTOList(historyRepository.findAll());
        } catch(Exception e) {
            log.error("Histories weren't got");
            throw new AllHistoriesException("Histories weren't got");
        }
    }

    @Override
    @Transactional
    public HistoryDTO getById(Long id) {
        log.info("Getting history by id: " + id);
        try {
            History history = historyRepository.findById(id).
                    orElseThrow(() -> new HistoryNotFoundException("Не существует пользователя с данным id: " + id));
            log.info("Success!!");
            return historyMapper.toDTO(history);
        } catch (HistoryNotFoundException e) {
            log.error("Error getting history by id: " + id);
            throw e;

        }
    }

    @Override
    @Transactional
    public HistoryDTO update(HistoryDTO historyDTO, Long id) {
        log.info("Updating history...");
        try {
            if (historyRepository.existsById(id)) {
                History history = historyMapper.toEntity(historyDTO);
                history.setId(id);
                historyRepository.save(history);
                log.info("Success!!");
                return historyMapper.toDTO(history);
            } else {
                throw new HistoryNotFoundException("History with id " + id + " doesn't exist :(");
            }
        } catch (Exception e) {
            log.error("History with id {} wasn't updated", id);
            throw new HistoryNotUpdatedException("History wasn't updated");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting history...");
        try {
            historyRepository.deleteById(id);
            log.info("Success!!");
        } catch (Exception e) {
            log.error("History with id {} wasn't deleted", id);
            throw new HistoryNotDeletedException("History wasn't deleted");
        }
    }
}


