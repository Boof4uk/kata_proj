package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.service.SuspiciousCardTransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Контроллер для работы с подозрительными переводами по номеру карты
 */

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/api/suspicious-card-transfer")
public class SuspiciousCardTransferController {

    private final SuspiciousCardTransferService suspiciousCardTransferService;

    /**
     * Получить все подозрительные переводы по номеру карты
     *
     * @return объект ResponseEntity с коллекцией SuspiciousCardTransferDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "получить все подозрительные переводы по номеру карты")
    public ResponseEntity<List<SuspiciousCardTransferDTO>> getAll() {
        return new ResponseEntity<>(suspiciousCardTransferService.getAll(), HttpStatus.OK);
    }

    /**
     * Получить подозрительный переводы по номеру карты по id
     *
     * @param id идентификатор подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousCardTransferDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить подозрительный переводы по номеру карты по id")
    public ResponseEntity<SuspiciousCardTransferDTO> getById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(suspiciousCardTransferService.getById(id), HttpStatus.OK);
    }

    /**
     * Сохранить подозрительный переводы по номеру карты по id
     *
     * @param suspiciousCardTransferDTO объект с данными подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousCardTransferDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Сохранить подозрительный перевод по номеру карты")
    public ResponseEntity<SuspiciousCardTransferDTO> save(@Valid @RequestBody SuspiciousCardTransferDTO suspiciousCardTransferDTO) {
        return new ResponseEntity<>(suspiciousCardTransferService.add(suspiciousCardTransferDTO), HttpStatus.OK);
    }

    /**
     * Обновить подозрительный перевод по номеру карты по id
     *
     * @param suspiciousCardTransferDTO объект с данными подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousCardTransferDTO и статусом ответа
     */

    @PatchMapping("/{id}")
    @ApiOperation(value = "Обновить подозрительный перевод по номеру карты")
    public ResponseEntity<SuspiciousCardTransferDTO> update(@Valid @PathVariable Long id, @RequestBody SuspiciousCardTransferDTO suspiciousCardTransferDTO) {
        return new ResponseEntity<>(suspiciousCardTransferService.update(id,suspiciousCardTransferDTO), HttpStatus.OK);
    }

    /**
     * Удалить подозрительный перевод по номеру карты по id
     *
     * @param id            идентификатор подозрительного перевода
     * @return объект ResponseEntity со строкой ответа
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить подозрительный перевод по номеру карты  по id")
    public ResponseEntity<SuspiciousCardTransferDTO> delete(@Valid @PathVariable Long id) {
        suspiciousCardTransferService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
