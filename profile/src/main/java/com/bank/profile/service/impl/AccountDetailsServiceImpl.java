package com.bank.profile.service.impl;

import com.bank.profile.dto.request.AccountDetailsRequestDto;
import com.bank.profile.dto.response.AccountDetailsResponseDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.exeption.ResourceNotFoundException;
import com.bank.profile.mapper.AccountDetailsMapper;
import com.bank.profile.repository.AccountDetailsRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.AccountDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountDetailsServiceImpl implements AccountDetailsService {
    private final AccountDetailsRepository accountDetailsRepository;
    private final AccountDetailsMapper accountDetailsMapper;
    private final ProfileRepository profileRepository;

    @Override
    public AccountDetailsResponseDto create(AccountDetailsRequestDto accountDetailsRequestDto) {
        log.info("Creating accountDetails with request: {}", accountDetailsRequestDto);
        try {
            profileRepository.findById(accountDetailsRequestDto.profileId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Profile create AccountDetails", "ProfileId", accountDetailsRequestDto.profileId()));
            final AccountDetails accountDetails =
                    accountDetailsMapper.toEntity(accountDetailsRequestDto, profileRepository);
            final AccountDetails save = accountDetailsRepository.save(accountDetails);

            return accountDetailsMapper.toDto(save);
        } catch (ResourceNotFoundException e) {
            log.error("Error creating accountDetails: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public AccountDetailsResponseDto update(Long id, AccountDetailsRequestDto accountDetailsRequestDto) {
        log.info("Updating accountDetails with id: {} and request: {}", id, accountDetailsRequestDto);
        try {
            accountDetailsRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("AccountDetails update", "AccountDetailsId", id));
            profileRepository.findById(accountDetailsRequestDto.profileId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Profile update AccountDetails", "ProfileId", accountDetailsRequestDto.profileId()));
            final AccountDetails accountDetailsUpdate =
                    accountDetailsMapper.toEntity(accountDetailsRequestDto, profileRepository);
            accountDetailsUpdate.setId(id);

            return accountDetailsMapper.toDto(accountDetailsRepository.save(accountDetailsUpdate));
        } catch (ResourceNotFoundException e) {
            log.error("Error updating accountDetails with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public AccountDetailsResponseDto getById(Long id) {
        log.info("Getting accountDetails by id: {}", id);
        try {
            final AccountDetails accountDetails = accountDetailsRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("AccountDetails getById", "AccountDetailsId", id));
            return accountDetailsMapper.toDto(accountDetails);
        } catch (ResourceNotFoundException e) {
            log.error("Error getting accountDetails by id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting accountDetails with id: {}", id);
        try {
            final AccountDetails accountDetails = accountDetailsRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("AccountDetails delete", "AccountDetailsId", id));
            accountDetailsRepository.delete(accountDetails);
        } catch (ResourceNotFoundException e) {
            log.error("Error deleting accountDetails with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<AccountDetailsResponseDto> getByProfileId(Long profileId) {
        log.info("Getting accountDetails by profileId: {}", profileId);
        try {
            final List<AccountDetails> accountDetails =
                    accountDetailsRepository.findByProfileId(profileId).stream().toList();
            return accountDetailsMapper.toDTOList(accountDetails);
        } catch (ResourceNotFoundException e) {
            log.error("Error getting accountDetails by profileId: {}. Error: {}", profileId, e.getMessage());
            throw e;
        }
    }
}
