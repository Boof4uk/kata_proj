package com.bank.profile.service.impl;

import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.exception.EntityNameExistsException;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.service.ActualRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActualRegistrationServiceImpl implements ActualRegistrationService {
    private final ActualRegistrationRepository actualRegistrationRepository;
    private final ActualRegistrationMapper actualRegistrationMapper;

    @Override
    public ActualRegistrationResponseDto create(ActualRegistrationRequestDto actualRegistrationRequestDto) {

        log.info("Creating actualRegistration with request: {}", actualRegistrationRequestDto);
        try {
            return actualRegistrationMapper.toDto(actualRegistrationRepository
                    .save(actualRegistrationMapper.toEntity(actualRegistrationRequestDto)));
        } catch (ResourceNotFoundException e) {
            log.error("Error creating actualRegistration: {}", e.getMessage());
            throw e;
        }
    }


    @Override
    public ActualRegistrationResponseDto getById(Long id) {

        log.info("Getting actualRegistration by id: {}", id);
        try {
            final ActualRegistration registration = actualRegistrationRepository.findById(id)
                    .orElseThrow(()
                            -> new ResourceNotFoundException("ActualRegistration getById", "ActualRegistrationId", id));

            return actualRegistrationMapper.toDto(registration);
        } catch (ResourceNotFoundException e) {
            log.error("Error getting actualRegistration by id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public ActualRegistrationResponseDto update(Long id, ActualRegistrationRequestDto actualRegistrationRequestDto) {
        log.info("Updating actualRegistration with id: {} and request: {}", id, actualRegistrationRequestDto);
        try {
            final ActualRegistration registration = actualRegistrationRepository.findById(id)
                    .orElseThrow(()
                            -> new ResourceNotFoundException("ActualRegistration update", "ActualRegistrationId", id));
            final ActualRegistration registrationUpdate =
                    actualRegistrationMapper.toEntity(actualRegistrationRequestDto);
            registrationUpdate.setId(registration.getId());
            return actualRegistrationMapper.toDto(actualRegistrationRepository.save(registrationUpdate));
        } catch (ResourceNotFoundException e) {
            log.error("Error updating actualRegistration with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }


    @Override
    public List<ActualRegistrationResponseDto> getAll() {
        log.info("Getting all actualRegistrations");
        try {
            return actualRegistrationMapper.toDTOList(actualRegistrationRepository.findAll());
        } catch (EntityNameExistsException e) {
            log.error("Error getting all actualRegistrations: {}", e.getMessage());
            throw e;
        }
    }
}
