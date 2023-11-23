package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.service.LicenseService;
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
 * Контроллер для управления лицензиями банка.
 */
@RestController
@RequestMapping("/license")
@AllArgsConstructor
@Tag(name = "Управление лицензиями", description = "Конечные точки для управления лицензионными записями")
public class LicenseController {

    private final LicenseService licenseService;
    @Operation(summary = "Получить все записи лицензий", description = "Получить список всех записей лицензии")
    @ApiResponse(responseCode = "200"
            , description = "Успешная операция"
            , content = @Content(mediaType = "application/json"
            , schema = @Schema(implementation = LicenseDTO.class)))
    @GetMapping
    public ResponseEntity<List<LicenseDTO>> getAll() {
        return new ResponseEntity<>(licenseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получить конкретную запись лицензии по ID", description = "Получить запись лицензии по её ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200"
                    , description = "Успешная операция"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = LicenseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запись лицензии не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LicenseDTO> getLicense(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(licenseService.find(id), HttpStatus.OK);
    }

    @Operation(summary = "Создать новую запись лицензии", description = "Создать новую запись лицензии")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @PostMapping
    public ResponseEntity<HttpStatus> createLicense(@Valid @RequestBody LicenseDTO licenseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        licenseService.save(licenseDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать существующую запись лицензии", description = "Редактировать существующую запись лицензии")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись лицензии не найдена", content = @Content)
    @PutMapping
    public ResponseEntity<HttpStatus> editLicense(@Valid @RequestBody LicenseDTO licenseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        licenseService.update(licenseDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить запись лицензии по ID", description = "Удалить запись лицензии по её ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись лицензии не найдена", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLicense(@Valid @PathVariable Long id) {
        licenseService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
}
