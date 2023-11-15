package com.bank.authorization.service.impl;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.User;
import com.bank.authorization.mapper.UserMapper;
import com.bank.authorization.repository.UserRepository;
import com.bank.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto add(UserDto userDto) {
        User user = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userMapper.toDtoList(userList);
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        User userEntity = userMapper.toEntity(userDto);
        User user = userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new RuntimeException("User ID not found. id: " + userEntity.getId()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found. id: " + id));
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User ID not found. id: " + id));
        return userMapper.toDto(user);
    }
}
