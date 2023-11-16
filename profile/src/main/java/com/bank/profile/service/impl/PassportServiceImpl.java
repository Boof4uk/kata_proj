package com.bank.profile.service.impl;

import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.entity.Passport;
import com.bank.profile.exeption.EntityNameExistsException;
import com.bank.profile.exeption.ResourceNotFoundException;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final RegistrationRepository registrationRepository;
    private final PassportMapper passportMapper;

    @Override
    public PassportResponseDto create(PassportRequestDto passportRequestDto) {
        log.info("Creating passport with request: {}", passportRequestDto);
        try {
            registrationRepository.findById(passportRequestDto.registrationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Registration create passport", "registrationId", passportRequestDto.registrationId()));
            Passport passport = passportMapper.toEntity(passportRequestDto, registrationRepository);
            return passportMapper.toDto(passportRepository.save(passport));
        } catch (ResourceNotFoundException e) {
            log.error("Error creating passport: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public PassportResponseDto getById(Long id) {
        log.info("Getting passport by id: {}", id);
        try {
        return passportMapper
                .toDto(passportRepository.findById(id).orElseThrow((
                ) -> new ResourceNotFoundException("Passport getById", "PassportId", id)));
    }catch (ResourceNotFoundException e) {
            log.error("Error getting passport by id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public PassportResponseDto update(Long id, PassportRequestDto passportRequestDto) {
        log.info("Updating passport with id: {} and request: {}", id, passportRequestDto);
        try {


     registrationRepository.findById(passportRequestDto.registrationId())
                .orElseThrow(() -> new ResourceNotFoundException("Registration update passport", "registrationId", passportRequestDto.registrationId()));
        passportRepository.findById(id).orElseThrow((
        ) -> new ResourceNotFoundException("Passport update", "PassportId", id));
        return passportMapper.toDto(passportRepository
                .save(passportMapper.toEntity(passportRequestDto, registrationRepository)));
    }catch (ResourceNotFoundException e) {
            log.error("Error updating passport with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }



    @Override
    public List<PassportResponseDto> getAll() {
        log.info("Getting all passports");
        try {


        return passportMapper.toDTOList(passportRepository.findAll());
    }catch (EntityNameExistsException e) {
            log.error("Error getting all passports: {}", e.getMessage());
            throw e;
        }
    }
}
