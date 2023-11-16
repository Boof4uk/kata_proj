package com.bank.profile.controller;

import com.bank.profile.component.ApiErrorResponse;
import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.service.ProfileService;
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

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping()
    @Operation(summary = "Create Profile", description = "Creating Profile and unique identifier assigning. Follows model's " +
            "constraints to avoid unhandled errors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profile created and will be returned with id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProfileResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid json", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<ProfileResponseDto> create(@RequestBody ProfileRequestDto profileRequestDto) {
        ProfileResponseDto profileResponseDto = profileService.create(profileRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileResponseDto);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Profile by id", description = "Get Profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProfileResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<ProfileResponseDto> byId(@PathVariable Long id) {
        ProfileResponseDto profileResponseDto = profileService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(profileResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Profile", description = "Update Profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProfileResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid request body", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<ProfileResponseDto> update(@PathVariable Long id, @RequestBody ProfileRequestDto profileRequestDto) {
        ProfileResponseDto profileResponseDto = profileService.update(id, profileRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(profileResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete  profile", description = "Delete profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = " Profile deleted", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "409", description = "Invalid id", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profileService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Profile", description = "Get all Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile list returned", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProfileResponseDto.class)),
            })
    })
    public ResponseEntity<List<ProfileResponseDto>> getAll() {
        List<ProfileResponseDto> profileResponseDto = profileService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(profileResponseDto);
    }
}