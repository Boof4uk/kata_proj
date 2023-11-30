package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/auth-user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "Получить список пользователей", description = "Получить список пользователей")
    @ApiResponse(responseCode = "200",
            description = "Успешная операция",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class)))
    public ResponseEntity<List<UserDto>> getAll() {
        final List<UserDto> userDtoList = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @PostMapping
    @Operation(summary = "Создать пользователя", description = "Создать пользователя")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @ApiResponse(responseCode = "403", description = "Нет прав", content = @Content)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        userService.add(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать пользователя", description = "Редактировать пользователя")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    @ApiResponse(responseCode = "403", description = "Нет прав", content = @Content)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID", description = "Получить пользователя по ID")
    @ApiResponse(responseCode = "200", description = "Успешная операция")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        final UserDto userDto = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "удалить пользователя по ID", description = "удалить пользователя по ID")
    @ApiResponse(responseCode = "204", description = "Пользователь удален")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    @ApiResponse(responseCode = "403", description = "Нет прав", content = @Content)
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
