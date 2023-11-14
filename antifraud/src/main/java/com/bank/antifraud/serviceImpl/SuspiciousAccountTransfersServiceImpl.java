package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.entity.SuspiciousAccountTransfers;
import com.bank.antifraud.mapper.SuspiciousAccountTransfersMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransfersRepository;
import com.bank.antifraud.service.SuspiciousAccountTransfersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс сервиса для сущности SuspiciousAccountTransfers
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuspiciousAccountTransfersServiceImpl implements SuspiciousAccountTransfersService {
    /**
     * Репозиторий для сущности SuspiciousAccountTransfers
     */
    private final SuspiciousAccountTransfersRepository accountRepository;
    /**
     * Маппер для сущности SuspiciousAccountTransfers
     */
    private final SuspiciousAccountTransfersMapper accountMapper;

    /**
     * Сохранить подозрительный перевод по номеру счета
     *
     * @param suspiciousAccountTransfersDTO объект, содержащий данные подозрительного перевода по номеру счета
     * @return объект SuspiciousAccountTransfersDTO
     */
    @Override
    public SuspiciousAccountTransfersDTO add(SuspiciousAccountTransfersDTO suspiciousAccountTransfersDTO) {
        SuspiciousAccountTransfers accountTransfers = accountMapper.toEntity(suspiciousAccountTransfersDTO);
        return accountMapper.toDto(accountRepository.save(accountTransfers));
    }

    /**
     * Получить все подозрительные переводы по номеру счета
     *
     * @return список подозрительных переводов по номеру счета (объектов SuspiciousAccountTransfersDTO)
     */
    @Override
    public List<SuspiciousAccountTransfersDTO> getAll() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    /**
     * Обновить подозрительный перевод по номеру счета
     *
     * @param suspiciousAccountTransfersDTO объект, содержащий данные подозрительного перевода по номеру счета
     * @return объект SuspiciousAccountTransfersDTO
     */
    @Override
    public SuspiciousAccountTransfersDTO update(SuspiciousAccountTransfersDTO suspiciousAccountTransfersDTO) {
        SuspiciousAccountTransfers accountTransfers = accountRepository
                .findById(suspiciousAccountTransfersDTO.getId())
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + suspiciousAccountTransfersDTO.getId()));
        return accountMapper.toDto(accountRepository.save(accountTransfers));
    }

    /**
     * Удалить по id подозрительный перевод по номеру счета
     *
     * @param id подозрительного перевода по номеру счета
     */
    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    /**
     * Получить по id подозрительный перевод по номеру счета
     *
     * @param id подозрительного перевода по номеру счета
     * @return объект SuspiciousAccountTransfersDTO
     */
    @Override
    public SuspiciousAccountTransfersDTO getById(Long id) {
        SuspiciousAccountTransfers accountTransfers = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + id));
        return accountMapper.toDto(accountTransfers);
    }
}
