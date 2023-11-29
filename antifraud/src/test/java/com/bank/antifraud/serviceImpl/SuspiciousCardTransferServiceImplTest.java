package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.exception.EntityNotFoundException;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class SuspiciousCardTransferServiceImplTest {
    @InjectMocks
    private SuspiciousCardTransferServiceImpl suspiciousCardTransferService;
    @Mock(lenient = true)
    private SuspiciousCardTransferRepository suspiciousCardTransferRepository;
    @Mock(lenient = true)
    private SuspiciousCardTransferMapper suspiciousCardTransferMapper;
    @Mock
    private SuspiciousCardTransferDTO suspiciousCardTransferDTO;
    private SuspiciousCardTransfer suspiciousCardTransfer;
    private List<SuspiciousCardTransfer> suspiciousCardTransferList = new ArrayList<>();
    private List<SuspiciousCardTransferDTO> suspiciousCardTransferDTOList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        suspiciousCardTransfer = new SuspiciousCardTransfer(1L, 1L,
                true, true, "string", "string");
        suspiciousCardTransferDTO = new SuspiciousCardTransferDTO(1L, 1L,
                true, true, "string", "string");
        suspiciousCardTransferList.add(suspiciousCardTransfer);
        suspiciousCardTransferDTOList.add(suspiciousCardTransferDTO);

        lenient().when(suspiciousCardTransferMapper.toEntity(any())).thenReturn(suspiciousCardTransfer);
        lenient().when(suspiciousCardTransferMapper.toDto(any())).thenReturn(suspiciousCardTransferDTO);
        lenient().when(suspiciousCardTransferRepository.save(any())).thenReturn(suspiciousCardTransfer);
        lenient().when(suspiciousCardTransferRepository.findAll()).thenReturn(suspiciousCardTransferList);
        lenient().when(suspiciousCardTransferRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(suspiciousCardTransfer));
    }

    @Test
    public void createTest() {
        SuspiciousCardTransferDTO result = suspiciousCardTransferService.add(suspiciousCardTransferDTO);
        assertNotNull(result);

        verify(suspiciousCardTransferMapper).toEntity(suspiciousCardTransferDTO);
        verify(suspiciousCardTransferMapper).toDto(suspiciousCardTransfer);
        verify(suspiciousCardTransferRepository).save(suspiciousCardTransfer);
    }

    @Test
    public void getAllTest() {
        when(suspiciousCardTransferRepository.findAll()).thenReturn(suspiciousCardTransferList);
        when(suspiciousCardTransferMapper.toDtoList(suspiciousCardTransferList)).thenReturn(suspiciousCardTransferDTOList);

        List<SuspiciousCardTransferDTO> result = suspiciousCardTransferService.getAll();
        assertNotNull(result);
        assertEquals(suspiciousCardTransferDTOList, result);
        verify(suspiciousCardTransferRepository).findAll();
        verify(suspiciousCardTransferMapper).toDtoList(suspiciousCardTransferList);
    }

    @Test
    public void getByIdSuccessfulTest() {
        SuspiciousCardTransferDTO result = suspiciousCardTransferService.getById(1L);

        assertNotNull(result);
        assertEquals(suspiciousCardTransferDTO, result);
        verify(suspiciousCardTransferRepository).findById(1L);
        verify(suspiciousCardTransferMapper).toDto(suspiciousCardTransfer);
    }

    @Test
    public void getByIdThrowExceptionTest() {
        when(suspiciousCardTransferRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> suspiciousCardTransferService.getById(1L));

        verify(suspiciousCardTransferRepository).findById(1L);
    }

    @Test
    public void updateSuccessfulTest() {
        SuspiciousCardTransferDTO result = suspiciousCardTransferService.update(1L, suspiciousCardTransferDTO);
        assertNotNull(result);
        assertEquals(suspiciousCardTransferDTO, result);
    }

    @Test
    public void updateThrowExceptionTest() {
        when(suspiciousCardTransferRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> suspiciousCardTransferService.update(1L, suspiciousCardTransferDTO));

        verify(suspiciousCardTransferRepository).findById(1L);
        verify(suspiciousCardTransferRepository, never()).save(any(SuspiciousCardTransfer.class));
        verify(suspiciousCardTransferMapper, never()).toEntity(any(SuspiciousCardTransferDTO.class));
        verify(suspiciousCardTransferMapper, never()).toDto(any(SuspiciousCardTransfer.class));
    }

    @Test
    public void deleteSuccessfulTest() {
        when(suspiciousCardTransferRepository.findById(1L)).thenReturn(Optional.of(suspiciousCardTransfer));
        suspiciousCardTransferService.delete(1L);
        verify(suspiciousCardTransferRepository).deleteById(1L);
    }

    @Test
    public void deleteThrowExceptionTest() {
        when(suspiciousCardTransferRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> suspiciousCardTransferService.delete(1L));
        verify(suspiciousCardTransferRepository).findById(1L);
    }
}