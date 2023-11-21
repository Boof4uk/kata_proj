package com.bank.profile.service.impl;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.EntityNameExistsException;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceIml implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;

    @Override
    public RegistrationResponseDto create(RegistrationRequestDto registrationRequestDto) {
        log.info("Creating registration with request: {}", registrationRequestDto);
        try {
            return registrationMapper.toDto(registrationRepository
                    .save(registrationMapper.toEntity(registrationRequestDto)));
        } catch (EntityNameExistsException e) {
            log.error("Error creating registration: {}", e.getMessage());
            throw e;

        }
    }

    @Override
    public List<RegistrationResponseDto> getAll() {
        log.info("Getting all registrations");
        try {
            return registrationMapper.toDTOList(registrationRepository.findAll());
        } catch (EntityNameExistsException e) {
            log.error("Error getting all registrations: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public RegistrationResponseDto getById(Long id) {
        log.info("Getting registration by id: {}", id);
        try {


            final Registration registration = registrationRepository.findById(id)
                    .orElseThrow(()
                            -> new ResourceNotFoundException("Registration getById", "RegistrationId", id));
            return registrationMapper.toDto(registration);
        } catch (ResourceNotFoundException e) {
            log.error("Error getting registration by id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public RegistrationResponseDto update(Long id, RegistrationRequestDto registrationRequestDto) {
        log.info("Updating registration with id: {} and request: {}", id, registrationRequestDto);
        try {
            final Registration registration = registrationRepository.findById(id)
                    .orElseThrow(()
                            -> new ResourceNotFoundException("Registration update", "RegistrationId", id));
            final Registration registrationUpdate = registrationMapper.toEntity(registrationRequestDto);
            registrationUpdate.setId(registration.getId());
            return registrationMapper.toDto(registrationRepository.save(registrationUpdate));
        } catch (ResourceNotFoundException e) {
            log.error("Error updating registration with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }


}
