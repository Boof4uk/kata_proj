package com.bank.profile.controller;

import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.dto.request.AccountDetailsRequestDto;
import com.bank.profile.dto.response.AccountDetailsResponseDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.service.AccountDetailsService;
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
@RequestMapping("/api/account-details")
@RequiredArgsConstructor
public class AccountDetailsController {
    private final AccountDetailsService accountDetailsService;

    @PostMapping()
    @Operation(summary = "Create account details", description = "Create a new account details ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account details created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AccountDetailsResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<AccountDetailsResponseDto> create(@Valid
            @RequestBody AccountDetailsRequestDto accountDetailsRequestDto) {
        final AccountDetailsResponseDto accountDetailsResponseDto = accountDetailsService.create(
                accountDetailsRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDetailsResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account details ", description = "Update an existing account details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account details updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AccountDetailsResponseDto.class))
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
    public ResponseEntity<AccountDetailsResponseDto> update(
            @PathVariable Long id,@Valid @RequestBody AccountDetailsRequestDto accountDetailsRequestDto) {
        final AccountDetailsResponseDto accountDetailsResponseDto = accountDetailsService.update(
                id, accountDetailsRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(accountDetailsResponseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account details  by id", description = "Retrieve an account details  by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account details  returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActualRegistrationResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<AccountDetailsResponseDto> getById(@PathVariable Long id) {
        final AccountDetailsResponseDto accountDetailsResponseDto = accountDetailsService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(accountDetailsResponseDto);
    }

    @GetMapping("/all/{profileId}")
    @Operation(summary = "Get account details", description =
            "Get all account details where profile id is equal to profileId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account details list returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProfileResponseDto.class)),
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<List<AccountDetailsResponseDto>> getByProfileId(@PathVariable Long profileId) {
        final List<AccountDetailsResponseDto> accountDetailsResponseDto =
                accountDetailsService.getByProfileId(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(accountDetailsResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account details ", description = "Delete a account details by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account details deleted"),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountDetailsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
