package com.bank.profile.service;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;

import java.util.List;

public interface RegistrationService {
    RegistrationResponseDto create(RegistrationRequestDto registrationRequestDto);
    List<RegistrationResponseDto> getAll();
    RegistrationResponseDto getById(Long id);
    RegistrationResponseDto update(Long id, RegistrationRequestDto registrationRequestDto);

}
