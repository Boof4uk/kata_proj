package com.bank.profile.service.impl;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileMapper profileMapper;

    @Mock
    private PassportRepository passportRepository;

    @Mock
    private ActualRegistrationRepository actualRegistrationRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;
   private Set<AccountDetails> accountDetailsSet = new HashSet<>();
    private List<Profile> profileList = new ArrayList<>();
    private List<ProfileResponseDto> profileResponseDtoList = new ArrayList<>();

    private ProfileRequestDto profileRequestDto;
    private Profile profile;
    private ProfileResponseDto profileResponseDto;
    private Passport passport;
    private PassportResponseDto passportResponseDto;
    private ActualRegistration actualRegistration;
    private ActualRegistrationResponseDto actualRegistrationResponseDto;
    @BeforeEach
    void setUp() {
        passport = mock(Passport.class);
        actualRegistration = mock(ActualRegistration.class);
        actualRegistrationResponseDto = mock(ActualRegistrationResponseDto.class);
        passportResponseDto = mock(PassportResponseDto.class);

        AccountDetails accountDetails = mock(AccountDetails.class);
        accountDetailsSet.add(accountDetails);

        profileRequestDto = new ProfileRequestDto(1234567890L, "user@example.com", "John Doe", 123456789012L, 12345678901L, 1L, 2L);
        profileResponseDto = new ProfileResponseDto(1L, 1234567890L, "user@example.com", "John Doe", 123456789012L, 12345678901L, passportResponseDto, actualRegistrationResponseDto);
        profile = new Profile(1L, 1234567890L, "user@example.com", "John Doe", 123456789012L, 12345678901L, accountDetailsSet, passport, actualRegistration);
        profileList.add(profile);
        profileResponseDtoList.add(profileResponseDto);

        lenient().when(profileMapper.toEntity(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(profile);
        lenient().when(profileMapper.toDto(Mockito.any())).thenReturn(profileResponseDto);
        lenient().when(profileRepository.save(Mockito.any())).thenReturn(profile);
        lenient().when(passportRepository.findById(Mockito.any())).thenReturn(Optional.of(passport));
        lenient().when(actualRegistrationRepository.findById(Mockito.any())).thenReturn(Optional.of(actualRegistration));
        lenient().when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
    }
    @Test
    void createShouldSuccessfullyCreateProfile() {

        ProfileResponseDto createdProfileResponseDto = profileService.create(profileRequestDto);

        assertEquals(profileResponseDto, createdProfileResponseDto);

        ArgumentCaptor<Profile> profileArgumentCaptor = ArgumentCaptor.forClass(Profile.class);
        verify(profileRepository).save(profileArgumentCaptor.capture());
        Profile capturedProfile = profileArgumentCaptor.getValue();

        assertEquals(profileRequestDto.phoneNumber(), capturedProfile.getPhoneNumber());
        assertEquals(profileRequestDto.email(), capturedProfile.getEmail());
        assertEquals(profileRequestDto.nameOnCard(), capturedProfile.getNameOnCard());
        assertEquals(profileRequestDto.inn(), capturedProfile.getInn());
        assertEquals(profileRequestDto.snils(), capturedProfile.getSnils());

        verify(passportRepository).findById(profileRequestDto.passportId());
        verify(actualRegistrationRepository).findById(profileRequestDto.actualRegistrationId());


    }

    @Test
    void getById() {

        ProfileResponseDto getProfileResponseDto = profileService.getById(1L);
        assertEquals(profileResponseDto, getProfileResponseDto);
        verify(profileRepository).findById(1L);

    }
    @Test
    void getByIdShouldThrowResourceNotFoundExceptionWhenNotFound() {
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> profileService.getById(1L));

        verify(profileRepository).findById(1L);
    }

    @Test
    void update() {
        ProfileResponseDto updateProfileResponseDto = profileService.update(1L, profileRequestDto);

        assertEquals(profileResponseDto, updateProfileResponseDto);

        ArgumentCaptor<Profile> profileArgumentCaptor = ArgumentCaptor.forClass(Profile.class);
        verify(profileRepository).save(profileArgumentCaptor.capture());
        Profile capturedProfile = profileArgumentCaptor.getValue();

        assertEquals(profileRequestDto.phoneNumber(), capturedProfile.getPhoneNumber());
        assertEquals(profileRequestDto.email(), capturedProfile.getEmail());
        assertEquals(profileRequestDto.nameOnCard(), capturedProfile.getNameOnCard());
        assertEquals(profileRequestDto.inn(), capturedProfile.getInn());
        assertEquals(profileRequestDto.snils(), capturedProfile.getSnils());

        verify(passportRepository).findById(profileRequestDto.passportId());
        verify(profileRepository).findById(1L);
        verify(actualRegistrationRepository).findById(profileRequestDto.actualRegistrationId());

    }
    @Test
    void updateShouldThrowExceptionWhenProfileNotFound() {
        Long nonExistentId = 999L;


        when(profileRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> profileService.update(nonExistentId, profileRequestDto));
        verify(profileRepository).findById(nonExistentId);

    }

    @Test
    void delete() {
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        profileService.delete(1L);
        verify(profileRepository).delete(profile);
    }
    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenNotFound() {
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> profileService.delete(1L));
        verify(profileRepository).findById(1L);

    }

    @Test
    void getAll() {
        when(profileRepository.findAll()).thenReturn(profileList);
        when(profileMapper.toDTOList(profileList)).thenReturn(profileResponseDtoList);

        List<ProfileResponseDto> result = profileService.getAll();

        assertNotNull(result);
        assertEquals(profileResponseDtoList, result);

        verify(profileRepository).findAll();
        verify(profileMapper).toDTOList(profileList);
    }
}