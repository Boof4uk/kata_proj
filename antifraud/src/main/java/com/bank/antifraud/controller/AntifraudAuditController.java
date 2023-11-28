package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.service.AntifraudAuditService;
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
 * Контроллер для работы с аудитом
 */

@RestController
@RequestMapping("/api/antifraud-audit")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AntifraudAuditController {

    private final AntifraudAuditService antifraudAuditService;

    /**
     * Получить все аудиты
     *
     * @return объект ResponseEntity с коллекцией AntifraudAuditDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "Получить все")
    public ResponseEntity<List<AntifraudAuditDTO>> getAll() {
        return new ResponseEntity<>(antifraudAuditService.getAll(), HttpStatus.OK);
    }

    /**
     * Получить аудиты по id
     *
     * @return объект ResponseEntity с объектом AntifraudAuditDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить аудит по id")
    public ResponseEntity<AntifraudAuditDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(antifraudAuditService.getById(id), HttpStatus.OK);
    }

    /**
     * Сохранить аудит
     *
     * @param antifraudAuditDTO объект, содержащий данные для аудита
     * @return объект ResponseEntity с объектом AntifraudAuditDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Сохранить")
    public ResponseEntity<AntifraudAuditDTO> save(@Valid @RequestBody(required = false) AntifraudAuditDTO antifraudAuditDTO) {
        return new ResponseEntity<>(antifraudAuditService.add(antifraudAuditDTO), HttpStatus.OK);
    }

    /**
     * Обновить аудит
     *
     * @param antifraudAuditDTO объект, содержащий данные для аудита
     * @return объект ResponseEntity с объектом AntifraudAuditDTO и статусом ответа
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить по id")
    public ResponseEntity<AntifraudAuditDTO> update(
            @PathVariable Long id, @Valid @RequestBody(required = false) AntifraudAuditDTO antifraudAuditDTO) {
        final AntifraudAuditDTO antifraudAuditDTO1 =
                antifraudAuditService.update(id, antifraudAuditDTO);
        return ResponseEntity.status(HttpStatus.OK).body(antifraudAuditDTO1);
    }

    /**
     * Удалить  аудит по id
     *
     * @param id идентификатор аудита
     * @return объект ResponseEntity со строкой ответа
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить аудит по id")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        antifraudAuditService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

