package com.bank.profile.service;

import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;

import java.util.List;

public interface PassportService {
    PassportResponseDto create(PassportRequestDto passportRequestDto);
    PassportResponseDto getById(Long id);
    PassportResponseDto update(Long id, PassportRequestDto passportRequestDto);

    List<PassportResponseDto> getAll();
}
