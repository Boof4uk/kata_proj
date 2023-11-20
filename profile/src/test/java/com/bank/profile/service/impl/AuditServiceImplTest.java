package com.bank.profile.service.impl;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.entity.Audit;
import com.bank.profile.entity.Passport;
import com.bank.profile.exeption.ResourceNotFoundException;
import com.bank.profile.mapper.AuditMapper;
import com.bank.profile.repository.AuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {
    @Mock
    private AuditRepository auditRepository;

    @Mock
    private AuditMapper auditMapper;

    @InjectMocks
    private AuditServiceImpl auditService;

    private AuditRequestDto auditRequestDto;
    private AuditResponseDto auditResponseDto;
    private Audit audit;
    private List<Audit> auditList;
    private List<AuditResponseDto> auditResponseDtoList;
    private Passport passport;
    private ActualRegistrationResponseDto actualRegistrationResponseDto;

    @BeforeEach
    void setUp() {
        auditRequestDto = new AuditRequestDto("test",
                "test","test","test",
                LocalDateTime.of(2022,2,22,22,22,22),
                LocalDateTime.of(2023,3,23,23,23,23),
                "test","test");

        auditResponseDto = new AuditResponseDto(1L,"test",
                "test","test","test",
                 "2022-2-22 22:22:22","2023-3-23:23:23",
                "test","test");

        audit = new Audit(1L,"test",
                "test","test","test",
                LocalDateTime.of(2022,2,22,22,22,22),
                LocalDateTime.of(2023,3,23,23,23,23),
                "test","test");
        auditResponseDtoList = List.of(auditResponseDto);
        auditList = List.of(audit);
    }

    @Test
    void createShouldReturnAuditResponseDto() {
        when(auditMapper.toEntity(auditRequestDto)).thenReturn(audit);
        when(auditRepository.save(audit)).thenReturn(audit);
        when(auditMapper.toDto(audit)).thenReturn(auditResponseDto);
        AuditResponseDto result = auditService.create(auditRequestDto);
        assertNotNull(result);
        verify(auditMapper).toEntity(auditRequestDto);
        verify(auditRepository).save(audit);
        verify(auditMapper).toDto(audit);

    }
    @Test
    void getByIdShouldReturnAuditResponseDtoWhenFound() {
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));
        when(auditMapper.toDto(audit)).thenReturn(auditResponseDto);

        AuditResponseDto result = auditService.getById(1L);

        assertNotNull(result);
        assertEquals(auditResponseDto, result);
        verify(auditRepository).findById(1L);
        verify(auditMapper).toDto(audit);
    }

    @Test
    void getByIdShouldThrowResourceNotFoundExceptionWhenNotFound() {
        when(auditRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> auditService.getById(1L));

        verify(auditRepository).findById(1L);
    }

    @Test
    void getByIdShouldReturnAuditListResponseDtoWhenFound(){
        when(auditRepository.findAll()).thenReturn(auditList);
        when(auditMapper.toDTOList(auditList)).thenReturn(auditResponseDtoList);

        List<AuditResponseDto> result = auditService.getAll();

        assertNotNull(result);
        assertEquals(auditResponseDtoList, result);

        verify(auditRepository).findAll();
        verify(auditMapper).toDTOList(auditList);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenNotFound() {
        when(auditRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> auditService.update(1L, auditRequestDto));

        verify(auditRepository).findById(1L);
        verify(auditRepository, never()).save(any(Audit.class));
        verify(auditMapper, never()).toEntity(any(AuditRequestDto.class));
        verify(auditMapper, never()).toDto(any(Audit.class));
    }
    @Test
    void updateShouldReturnAuditResponseDto(){
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));
        when(auditMapper.toEntity(auditRequestDto)).thenReturn(audit);
        when(auditRepository.save(audit)).thenReturn(audit);
        when(auditMapper.toDto(audit)).thenReturn(auditResponseDto);

        AuditResponseDto result = auditService.update(1L, auditRequestDto);

        assertNotNull(result);
        assertEquals(auditResponseDto, result);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenNotFound() {
        when(auditRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> auditService.delete(1L));
        verify(auditRepository).findById(1L);
        verify(auditRepository, never()).save(any(Audit.class));
        verify(auditMapper, never()).toEntity(any(AuditRequestDto.class));
        verify(auditMapper, never()).toDto(any(Audit.class));
    }
    @Test
    void deleteShouldDeleteAuditWhenFound(){
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));
        auditService.delete(1L);
        verify(auditRepository).delete(audit);
    }



}