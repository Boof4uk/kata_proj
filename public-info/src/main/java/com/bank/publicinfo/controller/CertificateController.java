package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.service.CertificateService;
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
 * Контроллер для управления сертификатами.
 */
@RestController
@RequestMapping("/certificate")
@AllArgsConstructor
@Tag(name = "Управление сертификатами", description = "Конечные точки для управления сертификатными записями")
public class CertificateController {

    private final CertificateService certificateService;
    @Operation(summary = "Получить все записи сертификатов", description = "Получить список всех записей сертификатов")
    @ApiResponse(responseCode = "200"
            , description = "Успешная операция"
            , content = @Content(mediaType = "application/json"
            , schema = @Schema(implementation = CertificateDTO.class)))
    @GetMapping
    public ResponseEntity<List<CertificateDTO>> getAll() {
        return new ResponseEntity<>(certificateService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получить конкретную запись сертификатов по ID", description = "Получить запись сертификатов по её ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200"
                    , description = "Успешная операция"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = CertificateDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запись сертификатов не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CertificateDTO> getCertificate(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(certificateService.find(id), HttpStatus.OK);
    }

    @Operation(summary = "Создать новую запись сертификатов", description = "Создать новую запись сертификатов")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @PostMapping
    public ResponseEntity<HttpStatus> createCertificate(@Valid @RequestBody CertificateDTO certificateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        certificateService.save(certificateDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать существующую запись сертификатов", description = "Редактировать существующую запись сертификатов")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись сертификатов не найдена", content = @Content)
    @PutMapping
    public ResponseEntity<HttpStatus> editCertificate(@Valid @RequestBody CertificateDTO certificateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        certificateService.update(certificateDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить запись сертификатов по ID", description = "Удалить запись сертификатов по её ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Запись сертификатов не найдена", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCertificate(@Valid @PathVariable Long id) {
        certificateService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
