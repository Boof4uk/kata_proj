package com.bank.authorization.service.impl;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.User;
import com.bank.authorization.exception.EntityNotFoundException;
import com.bank.authorization.mapper.UserMapper;
import com.bank.authorization.repository.UserRepository;
import com.bank.authorization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String ENTITY_NAME = "User";

    private final UserRepository userRepository;

    @Lazy
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository,
                           @Lazy PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDto add(UserDto userDto) {
        log.info("Creating user {}", userDto);
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
        } catch (RuntimeException e) {
            log.error("Error creating user: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<UserDto> getAll() {
        log.info("Getting all users");
        try {
            final List<User> userList = userRepository.findAll();
            return userMapper.toDtoList(userList);
        } catch (RuntimeException e) {
            log.error("Error getting all users: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        log.info("Updating user with id: {} and body: {}", userDto.getId(), userDto);
        try {
            if (userDto.getId() == null) {
                throw new IllegalArgumentException("The given id must not be null!");
            }
            final User userEntity = userMapper.toEntity(userDto);
            final Optional<User> optionalUser = userRepository.findById(userEntity.getId());
            optionalUser
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, userEntity.getId()));
            return userMapper.toDto(userRepository.save(userEntity));
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            log.error("Error updating user with id: {}. Error: {}", userDto.getId(), e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting user with id: {}", id);
        try {
            userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, id));
            userRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting user with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public UserDto getById(Long id) {
        log.info("Getting user with id: {}", id);
        try {
            final Optional<User> userOptional = userRepository.findById(id);
            final User user = userOptional
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, id));
            return userMapper.toDto(user);
        } catch (EntityNotFoundException e) {
            log.error("Error getting user with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByRole(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getAuthorities());

    }
}
