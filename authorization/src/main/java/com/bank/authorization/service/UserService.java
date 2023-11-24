package com.bank.authorization.service;

import com.bank.authorization.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto add(UserDto userDto);

    List<UserDto> getAll();

    UserDto update(UserDto userDto);

    void deleteById(Long id);

    UserDto getById(Long id);


}
