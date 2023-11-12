package com.bank.transfer.serviceImpl;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.service.AccountTransferService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса AccountTransferService для сущности AccountTransfer
 */

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccountTransferServiceImpl implements AccountTransferService {

    /**
     * Репозиторий для сущности AccountTransfer
     */

    private final AccountTransferRepository accountTransferRepository;

    /**
     * Маппер для сущности AccountTransfer
     */

    private final AccountTransferMapper accountTransferMapper;

    /**
     * Добавить перевод между счетами
     * @param accountTransferDTO объект, содержащий данные для перевода
     * @return объект AccountTransferDTO
     */

    @Override
    public AccountTransferDTO add(AccountTransferDTO accountTransferDTO) {
        return accountTransferMapper.toDto(accountTransferRepository.save(accountTransferMapper.toEntity(accountTransferDTO)));
    }

    /**
     * Получить все переводы
     * @return коллекция объектов AccountTransferDTO
     */

    @Override
    public List<AccountTransferDTO> all() {
        List<AccountTransfer> list = accountTransferRepository.findAll();
        return accountTransferMapper.toDTOList(list);
    }

    /**
     * Обновить данные для перевода
     * @param accountTransferDTO объект, содержащий данные для перевода
     * @return объект AccountTransferDTO
     */

    @Override
    public AccountTransferDTO update(AccountTransferDTO accountTransferDTO) {
        AccountTransfer accountTransfer = accountTransferMapper.toEntity(accountTransferDTO);
        AccountTransfer account = accountTransferRepository.findById(accountTransfer.getId())
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + accountTransfer.getId()));
        accountTransferRepository.save(account);
        return accountTransferMapper.toDto(account);
    }

    /**
     * Удалить перевод по идентификатору
     * @param id идентификатор перевода
     */

    @Override
    public void delete(Long id) {
        accountTransferRepository.deleteById(id);
    }

    /**
     * Получить перевод по идентификатору
     * @param id идентификатор перевода
     * @return объект AccountTransferDTO
     */

    @Override
    public AccountTransferDTO showById(Long id) {
        AccountTransfer accountTransfer = accountTransferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + id));
        return accountTransferMapper.toDto(accountTransfer);
    }
}
