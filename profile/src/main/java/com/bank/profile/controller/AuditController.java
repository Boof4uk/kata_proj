package com.bank.profile.controller;

import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.service.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping()
    @Operation(summary = "Create audit ", description = "Create a new audit ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Audit record created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuditResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<AuditResponseDto> create(@RequestBody AuditRequestDto auditRequestDto) {
        final AuditResponseDto audResponseDto = auditService.create(auditRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(audResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Audit Record", description = "Update an existing audit record by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Audit record updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuditResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<AuditResponseDto> update(@PathVariable Long id,@Valid @RequestBody AuditRequestDto auditRequestDto) {
        AuditResponseDto audResponseDto = auditService.update(id,auditRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(audResponseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Audit Record by id", description = "Retrieve an audit record by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Audit record returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuditResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<AuditResponseDto> getById(@PathVariable Long id) {
        final AuditResponseDto audResponseDto = auditService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(audResponseDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Audit Records", description = "Retrieve a list of all audit records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of audit records returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuditResponseDto.class))
            })
    })
    public ResponseEntity<List<AuditResponseDto>> getAll() {
        final List<AuditResponseDto> audResponseDto = auditService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(audResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete audit", description = "Delete a audit by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Audit deleted"),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        auditService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
