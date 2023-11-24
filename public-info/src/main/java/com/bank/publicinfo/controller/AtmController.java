package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.service.AtmService;
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

import javax.validation.Valid;
import java.util.List;
/**
 * Контроллер для управления АТМ записями.
 */
@RestController
@RequestMapping("/atm")
@AllArgsConstructor
@Tag(name = "Управление банкоматом", description = "Конечные точки для управления банкоматами")
public class AtmController {

    private final AtmService atmService;
    @Operation(summary = "Получить все записи банкомата", description = "Получить список всех записей банкомата")
    @ApiResponse(responseCode = "200"
            , description = "Успешная операция"
            , content = @Content(mediaType = "application/json"
            , schema = @Schema(implementation = AtmDTO.class)))
    @GetMapping
    public ResponseEntity<List<AtmDTO>> getAll() {
        return new ResponseEntity<>(atmService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получить конкретную запись банкомата по ID", description = "Получить запись банкомата по её ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200"
                    , description = "Успешная операция"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = AtmDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запись банкомата не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AtmDTO> getAtm(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(atmService.find(id), HttpStatus.OK);
    }

    @Operation(summary = "Создать новую запись банкомата", description = "Создать новую запись банкомата")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @PostMapping
    public ResponseEntity<HttpStatus> createAtm(@Valid @RequestBody AtmDTO atmDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        atmService.save(atmDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать существующую запись банкомата", description = "Редактировать существующую запись банкомата")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись банкомата не найдена", content = @Content)
    @PutMapping
    public ResponseEntity<HttpStatus> editAtm(@Valid @RequestBody AtmDTO atmDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        atmService.update(atmDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить запись банкомата по ID", description = "Удалить запись банкомата по её ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись банкомата не найдена", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAtm(@Valid @PathVariable Long id) {
        atmService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
