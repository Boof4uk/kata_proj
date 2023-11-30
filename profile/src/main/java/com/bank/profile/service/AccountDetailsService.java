package com.bank.profile.service;

import com.bank.profile.dto.request.AccountDetailsRequestDto;
import com.bank.profile.dto.response.AccountDetailsResponseDto;

import java.util.List;

public interface AccountDetailsService {
    AccountDetailsResponseDto create(AccountDetailsRequestDto accountDetailsRequestDto);
    AccountDetailsResponseDto update(Long id, AccountDetailsRequestDto accountDetailsRequestDto);
    AccountDetailsResponseDto getById(Long id);
    void delete(Long id);
    List<AccountDetailsResponseDto> getByProfileId(Long profileId);


}
