package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.service.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для управления аудит записями.
 */
@RestController
@RequestMapping("/audit")
@AllArgsConstructor
@Tag(name = "Управление аудитом", description = "Конечные точки для управления аудит записями")
public class AuditController {

    private final AuditService auditService;
    @Operation(summary = "Получить все записи аудита", description = "Получить список всех записей аудита")
    @ApiResponse(responseCode = "200"
            , description = "Успешная операция"
            , content = @Content(mediaType = "application/json"
            , schema = @Schema(implementation = AuditDTO.class)))
    @GetMapping
    public ResponseEntity<List<AuditDTO>> getAll() {
        return new ResponseEntity<>(auditService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получить конкретную запись аудита по ID", description = "Получить запись аудита по её ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200"
                    , description = "Успешная операция"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = AuditDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запись аудита не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuditDTO> getAudit(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(auditService.find(id), HttpStatus.OK);
    }

    @Operation(summary = "Создать новую запись аудита", description = "Создать новую запись аудита")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @PostMapping
    public ResponseEntity<HttpStatus> createAudit(@RequestBody @Valid AuditDTO auditDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        auditService.save(auditDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(summary = "Редактировать существующую запись аудита", description = "Редактировать существующую запись аудита")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись аудита не найдена", content = @Content)
    @PutMapping
    public ResponseEntity<HttpStatus> editAudit(@Valid @RequestBody AuditDTO auditDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        auditService.update(auditDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить запись аудита по ID", description = "Удалить запись аудита по её ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись аудита не найдена", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAudit(@Valid @PathVariable Long id) {
        auditService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
