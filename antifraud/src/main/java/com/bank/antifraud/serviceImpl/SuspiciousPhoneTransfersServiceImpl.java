package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDTO;
import com.bank.antifraud.entity.SuspiciousPhoneTransfers;
import com.bank.antifraud.exception.EntityNotFoundException;
import com.bank.antifraud.mapper.SuspiciousPhoneTransfersMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransfersRepository;
import com.bank.antifraud.service.SuspiciousPhoneTransfersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс сервиса для сущности SuspiciousPhoneTransfers
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuspiciousPhoneTransfersServiceImpl implements SuspiciousPhoneTransfersService {
    /**
     * Репозиторий для сущности SuspiciousPhoneTransfers
     */
    private final SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository;
    /**
     * Маппер для сущности SuspiciousPhoneTransfers
     */
    private final SuspiciousPhoneTransfersMapper suspiciousPhoneTransfersMapper;

    /**
     * Сохранить подозрительный перевод по номеру телефона
     *
     * @param suspiciousPhoneTransfersDTO объект, содержащий данные подозрительного перевода по номеру телефона
     * @return объект SuspiciousPhoneTransfersDTO
     */
    @Override
    public SuspiciousPhoneTransfersDTO add(SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO) {
        SuspiciousPhoneTransfers suspiciousCardTransfer = suspiciousPhoneTransfersMapper.toEntity(suspiciousPhoneTransfersDTO);
        return suspiciousPhoneTransfersMapper.toDto(suspiciousPhoneTransfersRepository.save(suspiciousCardTransfer));
    }

    /**
     * Получить все подозрительные переводы по номеру телефона
     *
     * @return список подозрительных переводов по номеру телефона (объектов SuspiciousPhoneTransfersDTO)
     */

    @Override
    public List<SuspiciousPhoneTransfersDTO> getAll() {
        return suspiciousPhoneTransfersMapper.toDtoList(suspiciousPhoneTransfersRepository.findAll());
    }

    /**
     * Обновить подозрительный перевод по номеру телефона
     *
     * @param suspiciousPhoneTransfersDTO объект, содержащий данные подозрительного перевода по номеру телефона
     * @return объект SuspiciousPhoneTransfersDTO
     */

    @Override
    public SuspiciousPhoneTransfersDTO update(Long id, SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO) {
        suspiciousPhoneTransfersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PhoneTransfers update", id));
        SuspiciousPhoneTransfers phoneTransferUpdate = suspiciousPhoneTransfersMapper.toEntity(suspiciousPhoneTransfersDTO);
        phoneTransferUpdate.setId(id);
        return suspiciousPhoneTransfersMapper.toDto(suspiciousPhoneTransfersRepository.save(phoneTransferUpdate));
    }

    /**
     * Удалить по id подозрительный перевод по номеру телефона
     *
     * @param id подозрительного перевода по номеру телефона
     */
    @Override
    public void delete(Long id) {
        suspiciousPhoneTransfersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PhoneTransfers delete", id));
        suspiciousPhoneTransfersRepository.deleteById(id);
    }

    /**
     * Получить по id подозрительный перевод по номеру телефона
     *
     * @param id подозрительного перевода по номеру телефона
     * @return объект SuspiciousPhoneTransfersDTO
     */
    @Override
    public SuspiciousPhoneTransfersDTO getById(Long id) {

        SuspiciousPhoneTransfers cardTransfer = suspiciousPhoneTransfersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PhoneTransfers getById", id));
        return suspiciousPhoneTransfersMapper.toDto(cardTransfer);
    }
}
