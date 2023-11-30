package com.bank.authorization.controller;


import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.service.AuditService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api/auth-audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @Timed("getAllAudit")
    @GetMapping
    @Operation(summary = "Получить список аудитов", description = "Получить список всех аудитов")
    @ApiResponse(responseCode = "200",
            description = "Успешная операция",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuditDto.class)))
    public ResponseEntity<List<AuditDto>> showAll() {
        final List<AuditDto> auditDtoList = auditService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(auditDtoList);
    }

    @PostMapping
    @Operation(summary = "Создать новый аудит", description = "Создать новый аудит")
    @ApiResponse(responseCode = "201", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @ApiResponse(responseCode = "403", description = "Нет прав", content = @Content)
    public ResponseEntity<AuditDto> create(@Valid @RequestBody AuditDto auditDto) {
        auditService.add(auditDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(auditDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить аудит по ID", description = "Получить аудит по ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Аудит не найден", content = @Content)
    public ResponseEntity<AuditDto> getById(@PathVariable Long id) {
        final AuditDto auditDto = auditService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(auditDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать аудит", description = "Редактировать аудит")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Аудит не найден", content = @Content)
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @ApiResponse(responseCode = "403", description = "Нет прав", content = @Content)
    public ResponseEntity<AuditDto> update(@Valid @RequestBody AuditDto auditDto) {
        auditService.update(auditDto);
        return ResponseEntity.status(HttpStatus.OK).body(auditDto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить аудит по ID", description = "Удалить аудит по ID")
    @ApiResponse(responseCode = "204", description = "Аудит удален")
    @ApiResponse(responseCode = "404", description = "Аудит не найден", content = @Content)
    @ApiResponse(responseCode = "403", description = "Нет прав", content = @Content)
    public ResponseEntity<AuditDto> delete(@PathVariable Long id) {
        auditService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
