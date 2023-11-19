package com.bank.transfer.serviceImpl;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
import com.bank.transfer.service.PhoneTransferService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса PhoneTransferService для сущности PhoneTransfer
 */

@Service
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PhoneTransferServiceImpl implements PhoneTransferService {

    /**
     * Репозиторий для сущности PhoneTransfer
     */

    private final PhoneTransferRepository phoneTransferRepository;

    /**
     * Маппер для сущности PhoneTransfer
     */

    private final PhoneTransferMapper phoneTransferMapper;

    /**
     * Добавить перевод
     *
     * @param phoneTransferDTO объект, содержащий данные для перевода
     * @return объект PhoneTransferDTO
     */

    @Override
    public PhoneTransferDTO add(PhoneTransferDTO phoneTransferDTO) {
        return phoneTransferMapper.toDto(phoneTransferRepository.save(phoneTransferMapper.toEntity(phoneTransferDTO)));
    }

    /**
     * Получить все переводы
     *
     * @return список телефонных переводов (объектов PhoneTransferDTO)
     */

    @Override
    public List<PhoneTransferDTO> all() {
        List<PhoneTransfer> list = phoneTransferRepository.findAll();
        return phoneTransferMapper.toDTOList(list);
    }

    /**
     * Обновить данные для перевода
     *
     * @param phoneTransferDTO объект, содержащий данные для перевода
     * @return объект PhoneTransferDTO
     */

    @Override
    public PhoneTransferDTO update(PhoneTransferDTO phoneTransferDTO) {
        PhoneTransfer phoneTransfer = phoneTransferMapper.toEntity(phoneTransferDTO);
        phoneTransferRepository.save(phoneTransfer);
        return phoneTransferMapper.toDto(phoneTransfer);
    }

    /**
     * Удалить перевод по идентификатору
     *
     * @param id идентификатор перевода
     */

    @Override
    public void delete(Long id) {
        phoneTransferRepository.deleteById(id);
    }

    /**
     * Получить перевод по идентификатору
     *
     * @param id идентификатор перевода
     * @return объект PhoneTransferDTO
     */

    @Override
    public PhoneTransferDTO showById(Long id) {
        PhoneTransfer phoneTransfer = phoneTransferRepository.findById(id).orElseThrow(() -> new RuntimeException("PhoneTransfer not found with id: " + id));
        return phoneTransferMapper.toDto(phoneTransfer);
    }
}
