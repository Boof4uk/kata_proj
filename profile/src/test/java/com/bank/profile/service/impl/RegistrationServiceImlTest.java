package com.bank.profile.service.impl;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.entity.Registration;
import com.bank.profile.exeption.ResourceNotFoundException;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImlTest {
    @InjectMocks
    private RegistrationServiceIml registrationService;
    @Mock
    private RegistrationMapper registrationMapper;
    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private RegistrationResponseDto registrationResponseDto;
    private RegistrationRequestDto registrationRequestDto;
    private Registration registration;
    private List<Registration> registrationList = new ArrayList<>();
    private List<RegistrationResponseDto> registrationResponseDtoList = new ArrayList<>();

@BeforeEach
void setUp() {

    registration =new Registration(
            1L,"Russia","Moscow Region","Moscow","Central","Moscow","Tverskaya","10","A","101",123456L);

    registrationRequestDto = new RegistrationRequestDto(
            "Russia","Moscow Region","Moscow","Central","Moscow","Tverskaya","10","A","101",123456L);
    registrationResponseDto = new RegistrationResponseDto(
            1L,"Russia","Moscow Region","Moscow","Central","Moscow","Tverskaya","10","A","101",123456L);
    registrationList.add(registration);
    registrationResponseDtoList.add(registrationResponseDto);
    lenient().when(registrationMapper.toEntity(Mockito.any())).thenReturn(registration);
    lenient().when(registrationMapper.toDto(Mockito.any())).thenReturn(registrationResponseDto);
    lenient().when(registrationRepository.save(Mockito.any())).thenReturn(registration);
    lenient().when(registrationRepository.findAll()).thenReturn(registrationList);
    lenient().when(registrationRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(registration));
}
    @Test
    void createShouldReturnAuditResponseDto() {
        RegistrationResponseDto result = registrationService.create(registrationRequestDto);
        assertNotNull(result);
        verify(registrationMapper).toEntity(registrationRequestDto);
        verify(registrationRepository).save(registration);
        verify(registrationMapper).toDto(registration);
    }

    @Test
    void getByIdShouldReturnAuditListResponseDtoWhenFound(){
        when(registrationRepository.findAll()).thenReturn(registrationList);
        when(registrationMapper.toDTOList(registrationList)).thenReturn(registrationResponseDtoList);

        List<RegistrationResponseDto> result = registrationService.getAll();

        assertNotNull(result);
        assertEquals(registrationResponseDtoList, result);

        verify(registrationRepository).findAll();
        verify(registrationMapper).toDTOList(registrationList);
    }
    @Test
    void getByIdShouldThrowResourceNotFoundExceptionWhenNotFound() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> registrationService.getById(1L));

        verify(registrationRepository).findById(1L);
    }

    @Test
    void getByIdShouldReturnAuditResponseDtoWhenFound() {

        RegistrationResponseDto result = registrationService.getById(1L);

        assertNotNull(result);
        assertEquals(registrationResponseDto, result);
        verify(registrationRepository).findById(1L);
        verify(registrationMapper).toDto(registration);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenNotFound() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->registrationService.update(1L, registrationRequestDto));

        verify(registrationRepository).findById(1L);
        verify(registrationRepository, never()).save(any(Registration.class));
        verify(registrationMapper, never()).toEntity(any(RegistrationRequestDto.class));
        verify(registrationMapper, never()).toDto(any(Registration.class));
    }
    @Test
    void updateShouldReturnAuditResponseDto(){

        RegistrationResponseDto result = registrationService.update(1L, registrationRequestDto);

        assertNotNull(result);
        assertEquals(registrationResponseDto, result);
    }


}