package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDTO;
import com.bank.antifraud.service.SuspiciousPhoneTransfersService;
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
 * Контроллер для работы с подозрительными переводами по номеру телефона
 */

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/api/suspicious-phone-transfers")
public class SuspiciousPhoneTransfersController {

    private final SuspiciousPhoneTransfersService suspiciousPhoneTransfersService;

    /**
     * Получить все подозрительные переводы по номеру телефона
     *
     * @return объект ResponseEntity с коллекцией SuspiciousPhoneTransfersDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "получить все подозрительные переводы по номеру телефона")
    public ResponseEntity<List<SuspiciousPhoneTransfersDTO>> getAll() {
        return new ResponseEntity<>(suspiciousPhoneTransfersService.getAll(), HttpStatus.OK);
    }

    /**
     * Получить подозрительный переводы по номеру телефона по id
     *
     * @param id идентификатор подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousPhoneTransfersDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить подозрительный переводы по номеру телефона по id")
    public ResponseEntity<SuspiciousPhoneTransfersDTO> getById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(suspiciousPhoneTransfersService.getById(id), HttpStatus.OK);
    }

    /**
     * Сохранить подозрительный переводы по номеру телефона по id
     *
     * @param suspiciousPhoneTransfersDTO объект с данными подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousPhoneTransfersDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Сохранить подозрительный перевод по номеру телефона")
    public ResponseEntity<SuspiciousPhoneTransfersDTO> save(@Valid @RequestBody SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO) {
        return new ResponseEntity<>(suspiciousPhoneTransfersService.add(suspiciousPhoneTransfersDTO), HttpStatus.OK);
    }

    /**
     * Обновить подозрительный перевод по номеру телефона по id
     *
     * @param suspiciousPhoneTransfersDTO объект с данными подозрительного перевода
     * @return объект ResponseEntity с объектом SuspiciousPhoneTransfersDTO и статусом ответа
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить подозрительный перевод по номеру телефона")
    public ResponseEntity<SuspiciousPhoneTransfersDTO> update(@Valid @PathVariable Long id, @RequestBody SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO) {
        return new ResponseEntity<>(suspiciousPhoneTransfersService.update(id, suspiciousPhoneTransfersDTO), HttpStatus.OK);
    }

    /**
     * Удалить подозрительный перевод по номеру телефона по id
     *
     * @param id идентификатор подозрительного перевода
     * @return объект ResponseEntity со строкой ответа
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить подозрительный перевод по номеру телефона по id")
    public ResponseEntity<SuspiciousPhoneTransfersDTO> delete(@Valid @PathVariable Long id) {
        suspiciousPhoneTransfersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
