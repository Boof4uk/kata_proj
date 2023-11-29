package com.bank.authorization.service.impl;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.Audit;
import com.bank.authorization.exception.EntityNotFoundException;
import com.bank.authorization.mapper.AuditMapper;
import com.bank.authorization.repository.AuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    @Mock
    private AuditMapper auditMapper;

    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditServiceImpl auditService;

    private Audit audit;
    private AuditDto auditDto;

    @BeforeEach
    void setUp() {
        audit = new Audit(1L,
                "entityType",
                "operationType",
                "createdBy",
                "modifiedBy",
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                "newEntityJson",
                "entityJson");

        auditDto = new AuditDto(1L,
                "entityType",
                "operationType",
                "createdBy",
                "modifiedBy",
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                "newEntityJson",
                "entityJson");

    }

    @Test
    void addTest() {
        doReturn(audit).when(auditMapper).toEntity(auditDto);

        auditService.add(auditDto);

        verify(auditRepository).save(audit);
        verify(auditMapper).toEntity(auditDto);
    }

    @Test
    void getAllTest() {
        doReturn(audit).when(auditMapper).toEntity(auditDto);
        auditService.add(auditDto);
        auditService.getAll();

        verify(auditRepository).findAll();

    }

    @Test
    void updateTest() {
        doReturn(audit).when(auditMapper).toEntity(auditDto);
        when(auditRepository.findById(audit.getId())).thenReturn(Optional.of(audit));

        auditService.update(auditDto);

        verify(auditRepository).save(audit);
        verify(auditMapper).toEntity(auditDto);

    }


    @Test
    void deleteByIdTest() {
        Long id = 1L;
        when(auditRepository.findById(id)).thenReturn(Optional.of(audit));

        auditService.deleteById(id);

        verify(auditRepository).deleteById(audit.getId());
    }

    @Test
    void deleteByIdIsNotValidTest() {
        Long id = 1L;
        when(auditRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> auditService.deleteById(id));
        verify(auditRepository).findById(id);

    }

    @Test
    void getByIdTest() {
        Long id = 1L;
        when(auditRepository.findById(id)).thenReturn(Optional.of(audit));
        when(auditMapper.toDto(audit)).thenReturn(auditDto);

        AuditDto dto = auditService.getById(id);

        assertNotNull(dto);
        verify(auditRepository).findById(id);
        verify(auditMapper).toDto(audit);
        assertThat(dto).isEqualTo(auditDto);
    }


    @Test
    public void getByIdIsNotValidTest() {
        Long id = 22L;
        when(auditRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> auditService.getById(id));
    }
}