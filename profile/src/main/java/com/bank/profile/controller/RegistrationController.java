package com.bank.profile.controller;

import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping()
    @Operation(summary = "Create registration",
            description = "Creating registration and unique identifier assigning. Follows model's " +
                    "constraints to avoid unhandled errors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "registration created and will be returned with id",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RegistrationResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid json", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<RegistrationResponseDto> create(
            @Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        final RegistrationResponseDto registrationResponseDto = registrationService.create(registrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponseDto);
    }

    @Operation(summary = "Get registration by id", description = "Get registration by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegistrationResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationResponseDto> getById(@PathVariable Long id) {
        final RegistrationResponseDto registrationResponseDto = registrationService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(registrationResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update registration", description = "Update registration by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegistrationResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid request body", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<RegistrationResponseDto> update(
            @Valid @PathVariable Long id, @RequestBody RegistrationRequestDto registrationRequestDto) {
        final RegistrationResponseDto registrationResponseDto =
                registrationService.update(id, registrationRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(registrationResponseDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all registration", description = "Get all registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration list returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegistrationResponseDto.class)),
            })

    })
    public ResponseEntity<List<RegistrationResponseDto>> getAll() {
        final List<RegistrationResponseDto> registrationResponseDto = registrationService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(registrationResponseDto);
    }
}
