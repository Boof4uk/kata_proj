package com.bank.profile.controller;

import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.service.ActualRegistrationService;
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
@RequestMapping("/api/actual-registration")
@RequiredArgsConstructor
public class ActualRegistrationController {
    private final ActualRegistrationService actualRegistrationService;

    @PostMapping()
    @Operation(summary = "Create actual registration ",
            description = "Creating actual registration and unique identifier assigning. Follows model's " +
                    "constraints to avoid unhandled errors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ActualRegistration record created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActualRegistrationResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<ActualRegistrationResponseDto> create(@Valid
            @RequestBody ActualRegistrationRequestDto actualRegistrationRequestDto) {
        final ActualRegistrationResponseDto registrationResponseDto =
                actualRegistrationService.create(actualRegistrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update actual registration ", description = "Update an existing actual registration  by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actual registration updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActualRegistrationResponseDto.class))
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
    public ResponseEntity<ActualRegistrationResponseDto> update(@Valid
             @PathVariable Long id, @RequestBody ActualRegistrationRequestDto actualRegistrationRequestDto) {
        final ActualRegistrationResponseDto registrationResponseDto =
                actualRegistrationService.update(id, actualRegistrationRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(registrationResponseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get actual registration  by id", description = "Retrieve an actual registration  by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actual registration  returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActualRegistrationResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<ActualRegistrationResponseDto> getById(@PathVariable Long id) {
        final ActualRegistrationResponseDto registrationResponseDto = actualRegistrationService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(registrationResponseDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all actual registration ", description = "Retrieve a list of all actual registration ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of actual registration  returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuditResponseDto.class))
            })
    })

    public ResponseEntity<List<ActualRegistrationResponseDto>> getAll() {
        final List<ActualRegistrationResponseDto> registrationResponseDto = actualRegistrationService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(registrationResponseDto);
    }


}
