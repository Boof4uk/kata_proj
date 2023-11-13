package com.bank.transfer.serviceImpl;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.service.CardTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CardTransferService для сущности CardTransfer
 */

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CardTransferServiceImpl implements CardTransferService {

    /**
     * Репозиторий для сущности CardTransfer
     */

    private final CardTransferRepository cardTransferRepository;

    /**
     * Маппер для сущности CardTransfer
     */

    private final CardTransferMapper cardTransferMapper;

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
        CardTransfer card = cardTransferRepository.findById(cardTransfer.getId())
                .orElseThrow(() -> new RuntimeException("CardTransfer not found with id: " + cardTransfer.getId()));
        cardTransferRepository.save(card);
        return cardTransferMapper.toDto(card);
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
        CardTransfer cardTransfer = cardTransferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CardTransfer not found with id: " + id));
        return cardTransferMapper.toDto(cardTransfer);
    }
}
