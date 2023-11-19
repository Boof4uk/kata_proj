package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.service.UserService;
import io.swagger.annotations.ApiOperation;
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

import java.util.List;

@RestController
@RequestMapping("/api/auth-user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation(value = "Показать всех пользователей")
    public ResponseEntity<List<UserDto>> getAll() {
        final List<UserDto> userDtoList = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @PostMapping
    @ApiOperation(value = "Создать пользователя")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        userService.add(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping
    @ApiOperation(value = "Обновить пользователя")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить пользователя по ID")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        final UserDto userDto = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "удалить пользователя по ID")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
