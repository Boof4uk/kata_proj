package com.bank.profile.service;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;

import java.util.List;

public interface ProfileService {
    ProfileResponseDto create(ProfileRequestDto profileRequestDto);
    ProfileResponseDto getById(Long id);
    ProfileResponseDto update(Long id, ProfileRequestDto profileRequestDto);
    void delete(Long id);
    List<ProfileResponseDto> getAll();

}
