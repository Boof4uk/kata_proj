package com.bank.authorization.service.impl;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.User;
import com.bank.authorization.exception.EntityNotFoundException;
import com.bank.authorization.mapper.UserMapper;
import com.bank.authorization.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;

    private User user2;

    private UserDto userDto1;

    private UserDto userDto2;

    @BeforeEach
    void setUp() {
        user1 = new User(1L,
                "ADMIN",
                1L,
                "password1");
        user2 = new User(2L,
                "USER",
                2L,
                "password2");
        userDto1 = new UserDto(1L,
                "ADMIN",
                1L,
                "password1");
        userDto2 = new UserDto(2L,
                "USER",
                2L,
                "password2");
    }

    @Test
    void addTest() {
        doReturn(user1).when(userMapper).toEntity(userDto1);
        doReturn(user2).when(userMapper).toEntity(userDto2);

        userService.add(userDto1);
        userService.add(userDto2);

        verify(userRepository).save(user1);
        verify(userRepository).save(user2);
        verify(userMapper).toEntity(userDto1);
        verify(userMapper).toEntity(userDto2);
    }

    @Test
    void getAllTest() {
        doReturn(user1).when(userMapper).toEntity(userDto1);
        doReturn(user2).when(userMapper).toEntity(userDto2);

        userService.add(userDto1);
        userService.add(userDto2);

        userService.getAll();

        verify(userRepository).findAll();
    }

    @Test
    void getAllIsNotValidTest() {
        doReturn(user1).when(userMapper).toEntity(userDto1);
        doReturn(user2).when(userMapper).toEntity(userDto2);

        userService.add(userDto1);
        userService.add(userDto2);

        userService.getAll();

        verify(userRepository).findAll();
    }

    @Test
    void updateTest() {
        doReturn(user1).when(userMapper).toEntity(userDto1);
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        userService.update(userDto1);

        verify(userRepository).save(user1);
        verify(userMapper).toEntity(userDto1);
    }

    @Test
    void deleteByIdTest() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user1));

        userService.deleteById(id);

        verify(userRepository).deleteById(user1.getId());
    }

    @Test
    void deleteByIdIsNotValidTest() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.deleteById(id));
        verify(userRepository).findById(id);

    }

    @Test
    void getByIdTest() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user1));
        when(userMapper.toDto(user1)).thenReturn(userDto1);

        UserDto dto = userService.getById(id);

        assertNotNull(dto);
        verify(userRepository).findById(id);
        verify(userMapper).toDto(user1);
        assertThat(dto).isEqualTo(userDto1);
    }

    @Test
    void testLoadUserByUsernameUserFound() {
        when(userRepository.findByRole("ADMIN")).thenReturn(user1);

        userService.loadUserByUsername("ADMIN");

        verify(userRepository).findByRole("ADMIN");

        assertEquals("ADMIN", user1.getUsername());
        assertEquals("password1", user1.getPassword());
    }

    @Test
    void testLoadUserByUsernameUserNotFound() {
        when(userRepository.findByRole("username")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("username"));

        verify(userRepository).findByRole("username");
    }

    @Test
    void getByIdIsNotValidTest() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getById(id));
    }
}