package com.bank.transfer.serviceImpl;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.service.CardTransferService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CardTransferService для сущности CardTransfer
 */

@Service
public class CardTransferServiceImpl implements CardTransferService {

    /**
     * Репозиторий для сущности CardTransfer
     */

    private final CardTransferRepository cardTransferRepository;

    /**
     * Маппер для сущности CardTransfer
     */

    private final CardTransferMapper cardTransferMapper;

    public CardTransferServiceImpl(CardTransferRepository cardTransferRepository, CardTransferMapper cardTransferMapper) {
        this.cardTransferRepository = cardTransferRepository;
        this.cardTransferMapper = cardTransferMapper;
    }

    /**
     * Добавить перевод
     * @param cardTransferDTO объект, содержащий данные для перевода
     * @return объект CardTransferDTO
     */

    @Override
    public CardTransferDTO add(CardTransferDTO cardTransferDTO) {
        return cardTransferMapper.toDto(cardTransferRepository.save(cardTransferMapper.toEntity(cardTransferDTO)));
    }

    /**
     * Получить все переводы
     * @return коллекция объектов CardTransferDTO
     */

    @Override
    public List<CardTransferDTO> all() {
        List<CardTransfer> list = cardTransferRepository.findAll();
        return cardTransferMapper.toDTOList(list);
    }

    /**
     * Обновить данные для перевода
     * @param cardTransferDTO объект, содержащий данные для перевода
     * @return объект CardTransferDTO
     */

    @Override
    public CardTransferDTO update(CardTransferDTO cardTransferDTO) {
        CardTransfer cardTransfer = cardTransferMapper.toEntity(cardTransferDTO);
        cardTransferRepository.save(cardTransfer);
        return cardTransferMapper.toDto(cardTransfer);
    }

    /**
     * Удалить перевод по идентификатору
     * @param id идентификатор перевода
     */

    @Override
    public void delete(Long id) {
        cardTransferRepository.deleteById(id);
    }

    /**
     * Получить перевод по идентификатору
     * @param id идентификатор перевода
     * @return объект CardTransferDTO
     */

    @Override
    public CardTransferDTO showById(Long id) {
        CardTransfer cardTransfer = cardTransferRepository.findById(id).get();
        return cardTransferMapper.toDto(cardTransfer);
    }
}
