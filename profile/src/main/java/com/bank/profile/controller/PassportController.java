package com.bank.profile.controller;

import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.service.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/passport")
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @PostMapping()
    @Operation(summary = "Create Passport", description = "Creating passport and unique identifier assigning. Follows model's " +
            "constraints to avoid unhandled errors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passport created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PassportResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid json", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<PassportResponseDto> create(@RequestBody PassportRequestDto passportRequestDto) {
        PassportResponseDto passportResponseDto = passportService.create(passportRequestDto);
        return ResponseEntity.ok(passportResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Passport", description = "Update passport details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passport updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PassportResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<PassportResponseDto> update(@Valid @RequestBody PassportRequestDto passportRequestDto, @PathVariable Long id) {
        PassportResponseDto passportResponseDto = passportService.update(id, passportRequestDto);
        return ResponseEntity.ok(passportResponseDto);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Passport by id", description = "Retrieve passport details by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passport details returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PassportResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<PassportResponseDto> getById(@PathVariable Long id) {
        PassportResponseDto passportResponseDto = passportService.getById(id);
        return ResponseEntity.ok(passportResponseDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Passports", description = "Retrieve a list of all passports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of passports returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PassportResponseDto.class))
            })
    })
    public ResponseEntity<List<PassportResponseDto>> getAll() {
        List<PassportResponseDto> passportResponseDto = passportService.getAll();
        return ResponseEntity.ok(passportResponseDto);
    }

}
