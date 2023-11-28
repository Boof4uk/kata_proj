package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.entity.AntifraudAudit;
import com.bank.antifraud.exception.EntityNotFoundException;
import com.bank.antifraud.mapper.AntifraudAuditMapper;
import com.bank.antifraud.repository.AntifraudAuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AntifraudAuditServiceImplTest {

    @InjectMocks
    private AntifraudAuditServiceImpl antifraudAuditService;
    @Mock(lenient = true)
    private AntifraudAuditRepository antifraudAuditRepository;
    @Mock(lenient = true)
    private AntifraudAuditMapper antifraudAuditMapper;
    @Mock
    private AntifraudAuditDTO antifraudAuditDTO;
    private AntifraudAudit antifraudAudit;
    private List<AntifraudAudit> antifraudAuditList = new ArrayList<>();
    private List<AntifraudAuditDTO> antifraudAuditDTOList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        antifraudAudit = new AntifraudAudit(1L, "string", "string",
                "string", "string", LocalDateTime.of(2014, Month.DECEMBER, 6, 12, 20),
                LocalDateTime.of(2019, Month.MAY, 23, 15, 33), "newJson", "Json");
        antifraudAuditDTO = new AntifraudAuditDTO(1L, "string", "string",
                "string", "string", LocalDateTime.of(2014, Month.DECEMBER, 6, 12, 20),
                LocalDateTime.of(2019, Month.MAY, 23, 15, 33), "newJson", "Json");
        antifraudAuditList.add(antifraudAudit);
        antifraudAuditDTOList.add(antifraudAuditDTO);
        lenient().when(antifraudAuditMapper.toEntity(any())).thenReturn(antifraudAudit);
        lenient().when(antifraudAuditMapper.toDTO(any())).thenReturn(antifraudAuditDTO);
        lenient().when(antifraudAuditRepository.save(any())).thenReturn(antifraudAudit);
        lenient().when(antifraudAuditRepository.findAll()).thenReturn(antifraudAuditList);
        lenient().when(antifraudAuditRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(antifraudAudit));
    }

    @Test
    public void createTest() {
        AntifraudAuditDTO result = antifraudAuditService.add(antifraudAuditDTO);
        assertNotNull(result);
        verify(antifraudAuditMapper).toEntity(antifraudAuditDTO);
        verify(antifraudAuditMapper).toDTO(antifraudAudit);
        verify(antifraudAuditRepository).save(antifraudAudit);
    }

    @Test
    public void getAllTest() {
        when(antifraudAuditRepository.findAll()).thenReturn(antifraudAuditList);
        when(antifraudAuditMapper.toDtoList(antifraudAuditList)).thenReturn(antifraudAuditDTOList);

        List<AntifraudAuditDTO> result = antifraudAuditService.getAll();
        assertNotNull(result);
        assertEquals(antifraudAuditDTOList, result);
        verify(antifraudAuditRepository).findAll();
        verify(antifraudAuditMapper).toDtoList(antifraudAuditList);
    }

    @Test
    public void getByIdSuccessfulTest() {
        AntifraudAuditDTO result = antifraudAuditService.getById(1L);

        assertNotNull(result);
        assertEquals(antifraudAuditDTO, result);
        verify(antifraudAuditRepository).findById(1L);
        verify(antifraudAuditMapper).toDTO(antifraudAudit);
    }

    @Test
    public void getByIdThrowExceptionTest() {
        when(antifraudAuditRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> antifraudAuditService.getById(1L));

        verify(antifraudAuditRepository).findById(1L);
    }

    @Test
    public void updateSuccessfulTest() {
        AntifraudAuditDTO result = antifraudAuditService.update(1L, antifraudAuditDTO);
        assertNotNull(result);
        assertEquals(antifraudAuditDTO, result);
    }

    @Test
    public void updateThrowExceptionTest() {
        when(antifraudAuditRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> antifraudAuditService.update(1L, antifraudAuditDTO));

        verify(antifraudAuditRepository).findById(1L);
        verify(antifraudAuditRepository, never()).save(any(AntifraudAudit.class));
        verify(antifraudAuditMapper, never()).toEntity(any(AntifraudAuditDTO.class));
        verify(antifraudAuditMapper, never()).toDTO(any(AntifraudAudit.class));
    }

    @Test
    public void deleteSuccessfulTest() {
        when(antifraudAuditRepository.findById(1L)).thenReturn(Optional.of(antifraudAudit));
        antifraudAuditService.delete(1L);
        verify(antifraudAuditRepository).deleteById(1L);
    }

    @Test
    public void deleteThrowExceptionTest() {
        when(antifraudAuditRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> antifraudAuditService.delete(1L));
        verify(antifraudAuditRepository).findById(1L);
    }


}