package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.service.SuspiciousAccountTransfersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с подозрительными переводами по номеру счета
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/api/suspicious-account-transfers")
public class SuspiciousAccountTransfersController {

    private final SuspiciousAccountTransfersService suspiciousAccountTransfersService;

    /**
     * Получить все подозрительные переводы на счет
     *
     * @return объект ResponseEntity с коллекцией SuspiciousAccountTransfersDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "получить все подозрительные переводы на счет")
    public ResponseEntity<List<SuspiciousAccountTransfersDTO>> getAll() {
        return new ResponseEntity<>(suspiciousAccountTransfersService.getAll(), HttpStatus.OK);
    }

    /**
     * Получить подозрительный переводы на счет по id
     *
     * @param id идентификатор подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousAccountTransfersDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить подозрительный переводы на счет по id")
    public ResponseEntity<SuspiciousAccountTransfersDTO> getById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(suspiciousAccountTransfersService.getById(id), HttpStatus.OK);
    }

    /**
     * Сохранить подозрительный переводы на счет по id
     *
     * @param accountTransfersDTO объект с данными подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousAccountTransfersDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Сохранить подозрительный перевод на счет")
    public ResponseEntity<SuspiciousAccountTransfersDTO> save(@Valid @RequestBody SuspiciousAccountTransfersDTO accountTransfersDTO) {
        return new ResponseEntity<>(suspiciousAccountTransfersService.add(accountTransfersDTO), HttpStatus.OK);
    }

    /**
     * Обновить подозрительный переводы на счет по id
     *
     * @param accountTransfersDTO объект с данными подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousAccountTransfersDTO и статусом ответа
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить подозрительный перевод на счет")
    public ResponseEntity<SuspiciousAccountTransfersDTO> update(@Valid @PathVariable Long id,
                                                                @RequestBody SuspiciousAccountTransfersDTO accountTransfersDTO) {
        return new ResponseEntity<>(suspiciousAccountTransfersService.update(id, accountTransfersDTO), HttpStatus.OK);
    }

    /**
     * Удалить подозрительный переводы на счет по id
     *
     * @param id идентификатор подозрительного перевода
     * @return объект ResponseEntity со строкой ответа
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить подозрительный переводы на счет по id")
    public ResponseEntity<SuspiciousAccountTransfersDTO> delete(@Valid @PathVariable Long id) {
        suspiciousAccountTransfersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
