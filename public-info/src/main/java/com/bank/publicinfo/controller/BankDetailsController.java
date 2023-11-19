package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.service.BankDetailsService;
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

import javax.validation.Valid;
import java.util.List;
/**
 * Контроллер для управления подробной информацией о банке.
 */
@RestController
@RequestMapping("/bankdetails")
@AllArgsConstructor
@Tag(name = "Управление реквизитами банка", description = "Конечные точки для управления реквизитами записями")
public class BankDetailsController {

    private final BankDetailsService bankDetailsService;

    @Operation(summary = "Получить все записи банковских реквизитов", description = "Получить список всех записей банковских реквизитов")
    @ApiResponse(responseCode = "200"
            , description = "Успешная операция"
            , content = @Content(mediaType = "application/json"
            , schema = @Schema(implementation = BankDetailsDTO.class)))
    @GetMapping
    public ResponseEntity<List<BankDetailsDTO>> getAll() {
        return new ResponseEntity<>(bankDetailsService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получить конкретную запись банковских реквизитов по ID", description = "Получить запись банковских реквизитов по её ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"
                    , description = "Успешная операция"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = BankDetailsDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запись банковских реквизитов не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BankDetailsDTO> getBankDetails(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(bankDetailsService.find(id), HttpStatus.OK);
    }

    @Operation(summary = "Создать новую запись банковских реквизитов", description = "Создать новую запись банковских реквизитов")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @PostMapping
    public ResponseEntity<HttpStatus> createBankDetails(@Valid @RequestBody BankDetailsDTO bankDetailsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        bankDetailsService.save(bankDetailsDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать существующую запись банковских реквизитов", description = "Редактировать существующую запись банковских реквизитов")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись банковских реквизитов не найдена", content = @Content)
    @PatchMapping
    public ResponseEntity<HttpStatus> editBankDetails(@Valid @RequestBody BankDetailsDTO bankDetailsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        bankDetailsService.update(bankDetailsDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить запись банковских реквизитов по ID", description = "Удалить запись банковских реквизитов по её ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись банковских реквизитов не найдена", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBankDetails(@Valid @PathVariable Long id) {
        bankDetailsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
