package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.service.BranchService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
/**
 * Контроллер для управления отделениями банка.
 */
@RestController
@RequestMapping("/branch")
@AllArgsConstructor
@Tag(name = "Управление отделениями банка", description = "Конечные точки для управления отделениями банка")
public class BranchController {
    
    private final BranchService branchService;
    @Operation(summary = "Получить все записи отделений", description = "Получить список всех записей отделений банка")
    @ApiResponse(responseCode = "200"
            , description = "Успешная операция"
            , content = @Content(mediaType = "application/json"
            , schema = @Schema(implementation = BranchDTO.class)))
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAll() {
        return new ResponseEntity<>(branchService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получить конкретную запись отделения по ID", description = "Получить запись отделения по её ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200"
                    , description = "Успешная операция"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = BranchDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запись отделения не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranch(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(branchService.find(id), HttpStatus.OK);
    }

    @Operation(summary = "Создать новую запись отделения", description = "Создать новую запись отделения")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @PostMapping
    public ResponseEntity<HttpStatus> createBranch(@Valid @RequestBody BranchDTO auditDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        branchService.save(auditDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать существующую запись отделения", description = "Редактировать существующую запись отделения")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись отделения не найдена", content = @Content)
    @PatchMapping
    public ResponseEntity<HttpStatus> editBranch(@Valid @RequestBody BranchDTO auditDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        branchService.update(auditDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить запись отделения по ID", description = "Удалить запись отделения по её ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись отделения не найдена", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBranch(@Valid @PathVariable Long id) {
        branchService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
