package com.bank.profile.service;

import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;

import java.util.List;

public interface ActualRegistrationService {
    ActualRegistrationResponseDto create(ActualRegistrationRequestDto actualRegistrationRequestDto);

    ActualRegistrationResponseDto getById(Long id);

    ActualRegistrationResponseDto update(Long id, ActualRegistrationRequestDto actualRegistrationRequestDto);


    List<ActualRegistrationResponseDto> getAll();

}
