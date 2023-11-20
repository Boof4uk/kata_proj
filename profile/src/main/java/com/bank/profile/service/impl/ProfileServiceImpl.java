package com.bank.profile.service.impl;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.Profile;
import com.bank.profile.exeption.EntityNameExistsException;
import com.bank.profile.exeption.ResourceNotFoundException;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final PassportRepository passportRepository;
    private final ActualRegistrationRepository actualRegistrationRepository;

    @Override
    public ProfileResponseDto create(ProfileRequestDto profileRequestDto) {
        log.info("Creating profile with request: {}", profileRequestDto);
        try {
            passportRepository.findById(profileRequestDto.passportId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Passport create Profile", "PassportId", profileRequestDto.passportId()));
            actualRegistrationRepository.findById(profileRequestDto.actualRegistrationId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "ActualRegistration create Profile", "ActualRegistrationId",
                            profileRequestDto.actualRegistrationId()));
            return profileMapper.toDto(profileRepository
                    .save(profileMapper.toEntity(profileRequestDto, passportRepository, actualRegistrationRepository)));
        } catch (ResourceNotFoundException e) {
            log.error("Error creating profile: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ProfileResponseDto getById(Long id) {
        log.info("Getting profile by id: {}", id);
        try {
            return profileMapper
                    .toDto(profileRepository.findById(id).orElseThrow((
                    ) -> new ResourceNotFoundException("Profile getById", "id", id)));
        } catch (ResourceNotFoundException e) {
            log.error("Error getting profile by id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public ProfileResponseDto update(Long id, ProfileRequestDto profileRequestDto) {
        log.info("Updating profile with id: {} and request: {}", id, profileRequestDto);
        try {
            final Profile profile = profileRepository.findById(id).orElseThrow((
            ) -> new ResourceNotFoundException("Profile update", "ProfileId", id));
            passportRepository.findById(profileRequestDto.passportId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Passport update Profile", "PassportId", profileRequestDto.passportId()));
            actualRegistrationRepository.findById(profileRequestDto.actualRegistrationId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "ActualRegistration update Profile",
                            "ActualRegistrationId", profileRequestDto.actualRegistrationId()));
            final Profile profileUpdate = profileMapper.toEntity(
                    profileRequestDto, passportRepository, actualRegistrationRepository);
            profileUpdate.setId(profile.getId());
            return profileMapper.toDto(profileRepository.save(profileUpdate));
        } catch (ResourceNotFoundException e) {
            log.error("Error updating profile with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting profile with id: {}", id);
        try {
            final Profile profile = profileRepository.findById(id).orElseThrow((
            ) -> new ResourceNotFoundException("Profile delete", "ProfileId", id));
            profileRepository.delete(profile);
        } catch (ResourceNotFoundException e) {
            log.error("Error deleting profile with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ProfileResponseDto> getAll() {
        log.info("Getting all profiles");
        try {
            return profileMapper.toDTOList(profileRepository.findAll());
        } catch (EntityNameExistsException e) {
            log.error("Error getting all profiles: {}", e.getMessage());
            throw e;
        }
    }
}
