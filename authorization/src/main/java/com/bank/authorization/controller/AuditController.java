package com.bank.authorization.controller;


import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.service.AuditService;
import io.swagger.annotations.ApiOperation;
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

import java.util.List;

@RestController
@RequestMapping("/api/auth-audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping
    @ApiOperation(value = "Получить список аудитов")
    public ResponseEntity<List<AuditDto>> showAll() {
        final List<AuditDto> auditDtoList = auditService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(auditDtoList);
    }

    @PostMapping
    @ApiOperation(value = "Создать новый аудит")
    public ResponseEntity<AuditDto> create(@RequestBody AuditDto auditDto) {
        auditService.add(auditDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(auditDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить аудит по ID")
    public ResponseEntity<AuditDto> getById(@PathVariable Long id) {
        final AuditDto auditDto = auditService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(auditDto);
    }

    @PutMapping
    @ApiOperation(value = "Обновить аудит")
    public ResponseEntity<AuditDto> update(@RequestBody AuditDto auditDto) {
        auditService.update(auditDto);
        return ResponseEntity.status(HttpStatus.OK).body(auditDto);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить аудит по ID")
    public ResponseEntity<AuditDto> delete(@PathVariable Long id) {
        auditService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
