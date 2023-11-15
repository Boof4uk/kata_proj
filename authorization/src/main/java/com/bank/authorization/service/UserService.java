package com.bank.authorization.service;

import com.bank.authorization.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto add(UserDto userDto);

    List<UserDto> getAll();

    UserDto update(UserDto userDto);

    void deleteById(Long id);

    UserDto getById(Long id);


}
