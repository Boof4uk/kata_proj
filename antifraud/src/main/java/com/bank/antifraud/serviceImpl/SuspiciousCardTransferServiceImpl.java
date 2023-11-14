package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import com.bank.antifraud.service.SuspiciousCardTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс сервиса для сущности SuspiciousCardTransfer
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuspiciousCardTransferServiceImpl implements SuspiciousCardTransferService {
    /**
     * Репозиторий для сущности SuspiciousCardTransfer
     */
    private final SuspiciousCardTransferRepository cardRepository;
    /**
     * Маппер для сущности SuspiciousCardTransfer
     */
    private final SuspiciousCardTransferMapper cardMapper;

    /**
     * Сохранить подозрительный перевод по номеру карты
     *
     * @param suspiciousCardTransferDTO объект, содержащий данные подозрительного перевода по номеру карты
     * @return объект SuspiciousCardTransferDTO
     */
    @Override
    public SuspiciousCardTransferDTO add(SuspiciousCardTransferDTO suspiciousCardTransferDTO) {
        SuspiciousCardTransfer suspiciousCardTransfer = cardMapper.toEntity(suspiciousCardTransferDTO);
        return cardMapper.toDto(cardRepository.save(suspiciousCardTransfer));
    }

    /**
     * Получить все подозрительные переводы по номеру карты
     *
     * @return список подозрительных переводов по номеру карты (объектов SuspiciousCardTransferDTO)
     */
    @Override
    public List<SuspiciousCardTransferDTO> getAll() {
        return cardMapper.toDtoList(cardRepository.findAll());
    }

    /**
     * Обновить подозрительный перевод по номеру карты
     *
     * @param suspiciousCardTransferDTO объект, содержащий данные подозрительного перевода по номеру карты
     * @return объект SuspiciousCardTransferDTO
     */
    @Override
    public SuspiciousCardTransferDTO update(SuspiciousCardTransferDTO suspiciousCardTransferDTO) {
        SuspiciousCardTransfer cardTransfer = cardRepository
                .findById(suspiciousCardTransferDTO.getId())
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + suspiciousCardTransferDTO.getId()));
        return cardMapper.toDto(cardRepository.save(cardTransfer));
    }

    /**
     * Удалить по id подозрительный перевод по номеру карты
     *
     * @param id подозрительного перевода по номеру карты
     */
    @Override
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    /**
     * Получить по id подозрительный перевод по номеру карты
     *
     * @param id подозрительного перевода по номеру карты
     * @return объект SuspiciousCardTransferDTO
     */
    @Override
    public SuspiciousCardTransferDTO getById(Long id) {
        SuspiciousCardTransfer cardTransfer = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + id));
        return cardMapper.toDto(cardTransfer);
    }

}

