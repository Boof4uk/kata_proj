package com.bank.profile.service.impl;

import com.bank.profile.dto.request.AccountDetailsRequestDto;
import com.bank.profile.dto.response.AccountDetailsResponseDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.entity.Profile;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.mapper.AccountDetailsMapper;
import com.bank.profile.repository.AccountDetailsRepository;
import com.bank.profile.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountDetailsServiceImplTest {
    @InjectMocks
    private AccountDetailsServiceImpl accountDetailsService;
    @Mock
    private AccountDetailsMapper accountDetailsMapper;
    @Mock
    private AccountDetailsRepository accountDetailsRepository;
    @Mock
    private ProfileRepository profileRepository;

    private AccountDetailsRequestDto accountDetailsRequestDto;
    private AccountDetails accountDetails;
    private AccountDetailsResponseDto accountDetailsResponseDto;
    private List<AccountDetails> accountDetailsList = new ArrayList<>();
    private List<AccountDetailsResponseDto> accountDetailsResponseDtoList = new ArrayList<>();

    private Profile profile;
    private ProfileResponseDto profileResponseDto;

    @BeforeEach
    void setUp() {
        profile = mock(Profile.class);
        profileResponseDto = mock(ProfileResponseDto.class);
        accountDetails = new AccountDetails(1L, 1L, profile);
        accountDetailsResponseDto = new AccountDetailsResponseDto(1L, 1L, profileResponseDto);
        accountDetailsRequestDto = new AccountDetailsRequestDto(1L, 1L);
        accountDetailsList.add(accountDetails);
        accountDetailsResponseDtoList.add(accountDetailsResponseDto);

        lenient().when(accountDetailsMapper.toEntity(any(), any())).thenReturn(accountDetails);
        lenient().when(accountDetailsMapper.toDto(any())).thenReturn(accountDetailsResponseDto);
        lenient().when(accountDetailsRepository.save(any())).thenReturn(accountDetails);
        lenient().when(accountDetailsRepository.findById(any())).thenReturn(Optional.of(accountDetails));
        lenient().when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));

    }

    @Test
    void create() {
        AccountDetailsResponseDto result = accountDetailsService.create(accountDetailsRequestDto);
        assertNotNull(result);
        assertEquals(accountDetailsResponseDto, result);

        ArgumentCaptor<AccountDetails> accountDetailsArgumentCaptor = ArgumentCaptor.forClass(AccountDetails.class);
        verify(accountDetailsRepository).save(accountDetailsArgumentCaptor.capture());
        AccountDetails accountDetailsArgumentCaptorValue = accountDetailsArgumentCaptor.getValue();

        assertEquals(accountDetailsRequestDto.accountId(), accountDetailsArgumentCaptorValue.getAccountId());

        verify(accountDetailsRepository).save(accountDetails);
        verify(accountDetailsMapper).toDto(accountDetails);
    }

    @Test
    void getByIdSuccess() {

        AccountDetailsResponseDto result = accountDetailsService.getById(1L);

        assertEquals(accountDetailsResponseDto, result);
        verify(accountDetailsRepository).findById(1L);
        verify(accountDetailsMapper).toDto(accountDetails);
    }

    @Test
    void getByIdNotFound() {
        when(accountDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountDetailsService.getById(1L));
    }

    @Test
    void update() {
        when(accountDetailsRepository.findById(1L)).thenReturn(Optional.of(accountDetails));
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(accountDetailsMapper.toEntity(any(AccountDetailsRequestDto.class), any(ProfileRepository.class))).thenReturn(accountDetails);
        when(accountDetailsRepository.save(accountDetails)).thenReturn(accountDetails);
        when(accountDetailsMapper.toDto(accountDetails)).thenReturn(accountDetailsResponseDto);

        AccountDetailsResponseDto result = accountDetailsService.update(1L, accountDetailsRequestDto);

        assertEquals(accountDetailsResponseDto, result);
        verify(accountDetailsRepository).save(accountDetails);
        verify(accountDetailsMapper).toDto(accountDetails);
    }

    @Test
    void updateNotFound() {
        when(accountDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountDetailsService.update(1L, accountDetailsRequestDto));
    }

    @Test
    void deleteSuccess() {
        when(accountDetailsRepository.findById(1L)).thenReturn(Optional.of(accountDetails));

        accountDetailsService.delete(1L);

        verify(accountDetailsRepository).delete(accountDetails);
    }

    @Test
    void getAllProfileId() {
        when(accountDetailsRepository.findByProfileId(1L)).thenReturn(new HashSet<>(accountDetailsList));
        when(accountDetailsMapper.toDTOList(accountDetailsList)).thenReturn(accountDetailsResponseDtoList);

        List<AccountDetailsResponseDto> result = accountDetailsService.getByProfileId(1L);

        assertEquals(accountDetailsResponseDtoList, result);
        verify(accountDetailsRepository).findByProfileId(1L);
        verify(accountDetailsMapper).toDTOList(accountDetailsList);
    }

    @Test
    void getAllProfileIdNotFound() {
        Long nonExistentId = 999L;
        when(accountDetailsService.getByProfileId(nonExistentId))
                .thenThrow(new ResourceNotFoundException("Profile", "id", nonExistentId));

        assertThrows(ResourceNotFoundException.class, () -> accountDetailsService.getByProfileId(nonExistentId));
    }
}