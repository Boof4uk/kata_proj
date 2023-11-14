package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.service.AntifraudAuditService;
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
    public ResponseEntity<AntifraudAuditDTO> getById(@Valid @PathVariable Long id) {
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
    public ResponseEntity<AntifraudAuditDTO> save(@Valid @RequestBody AntifraudAuditDTO antifraudAuditDTO) {
        return new ResponseEntity<>(antifraudAuditService.add(antifraudAuditDTO), HttpStatus.OK);
    }

    /**
     * Обновить аудит
     *
     * @param antifraudAuditDTO объект, содержащий данные для аудита
     * @return объект ResponseEntity с объектом AntifraudAuditDTO и статусом ответа
     */

    @PatchMapping("/{id}")
    @ApiOperation(value = "Обновить")
    public ResponseEntity<AntifraudAuditDTO> update(@Valid @RequestBody AntifraudAuditDTO antifraudAuditDTO) {
        return new ResponseEntity<>(antifraudAuditService.update(antifraudAuditDTO), HttpStatus.OK);
    }

    /**
     * Удалить  аудит по id
     *
     * @param id            идентификатор аудита
     * @param bindingResult обработка ошибок
     * @return объект ResponseEntity со строкой ответа
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить аудит по id")
    public ResponseEntity<AntifraudAuditDTO> delete(@Valid @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        antifraudAuditService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

