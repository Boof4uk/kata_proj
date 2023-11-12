package com.bank.transfer.controller;

import com.bank.transfer.dto.TransferAuditDTO;
import com.bank.transfer.service.TransferAuditService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с аудитом
 */

@RestController
@RequestMapping("/api/transfer-audit")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TransferAuditController {

    /**
     * сервис для работы с аудитом
     */

    private final TransferAuditService transferAuditService;

    /**
     * Получить все аудиты
     *
     * @return объект ResponseEntity с коллекцией TransferAuditDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "Получить все")
    public ResponseEntity<List<TransferAuditDTO>> all() {
        List<TransferAuditDTO> list = transferAuditService.all();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Получить аудит по идентификатору
     *
     * @param id идентификатор аудита
     * @return объект ResponseEntity с объектом TransferAuditDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить аудит по идентификатору")
    public ResponseEntity<TransferAuditDTO> showById(@Valid @PathVariable Long id) {
        TransferAuditDTO transferAuditDTO = transferAuditService.showById(id);
        return new ResponseEntity<>(transferAuditDTO, HttpStatus.OK);
    }

    /**
     * Добавить аудит
     *
     * @param transferAuditDTO объект, содержащий данные для аудита
     * @return объект ResponseEntity с объектом TransferAuditDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Добавить аудит")
    public ResponseEntity<TransferAuditDTO> newTransfer(@Valid @RequestBody TransferAuditDTO transferAuditDTO) {
        transferAuditService.add(transferAuditDTO);
        return new ResponseEntity<>(transferAuditDTO, HttpStatus.OK);
    }

    /**
     * Обновить данные аудита
     *
     * @param transferAuditDTO объект, содержащий данные для аудита
     * @return объект ResponseEntity с объектом TransferAuditDTO и статусом ответа
     */

    @PatchMapping("/{id}")
    @ApiOperation(value = "Обновить данные")
    public ResponseEntity<TransferAuditDTO> update(@Valid @RequestBody TransferAuditDTO transferAuditDTO) {
        transferAuditService.update(transferAuditDTO);
        return new ResponseEntity<>(transferAuditDTO, HttpStatus.OK);
    }

    /**
     * Удалить аудит по идентификатору
     *
     * @param id            идентификатор аудита
     * @param bindingResult обработка ошибок
     * @return объект ResponseEntity со строкой ответа
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить аудит")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input data");
        }
        transferAuditService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
