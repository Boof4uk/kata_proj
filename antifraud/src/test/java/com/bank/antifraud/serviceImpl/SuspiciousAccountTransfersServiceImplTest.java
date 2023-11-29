package com.bank.antifraud.serviceImpl;


import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.entity.SuspiciousAccountTransfers;
import com.bank.antifraud.exception.EntityNotFoundException;
import com.bank.antifraud.mapper.SuspiciousAccountTransfersMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransfersRepository;
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
class SuspiciousAccountTransfersServiceImplTest {
    @Mock(lenient = true)
    private SuspiciousAccountTransfersMapper suspiciousAccountTransfersMapper;
    @Mock(lenient = true)
    private SuspiciousAccountTransfersRepository suspiciousAccountTransfersRepository;
    @InjectMocks
    private SuspiciousAccountTransfersServiceImpl suspiciousAccountTransfersService;
    @Mock
    private SuspiciousAccountTransfersDTO suspiciousAccountTransfersDTO;
    private SuspiciousAccountTransfers suspiciousAccountTransfers;
    private List<SuspiciousAccountTransfers> suspiciousAccountTransfersList = new ArrayList<>();
    private List<SuspiciousAccountTransfersDTO> suspiciousAccountTransfersDTOList = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        suspiciousAccountTransfers = new SuspiciousAccountTransfers(1L, 1L,
                true, true, "string", "string");
        suspiciousAccountTransfersDTO = new SuspiciousAccountTransfersDTO(1L, 1L,
                true, true, "string", "string");
        suspiciousAccountTransfersList.add(suspiciousAccountTransfers);
        suspiciousAccountTransfersDTOList.add(suspiciousAccountTransfersDTO);

        lenient().when(suspiciousAccountTransfersMapper.toEntity(any())).thenReturn(suspiciousAccountTransfers);
        lenient().when(suspiciousAccountTransfersMapper.toDto(any())).thenReturn(suspiciousAccountTransfersDTO);
        lenient().when(suspiciousAccountTransfersRepository.save(any())).thenReturn(suspiciousAccountTransfers);
        lenient().when(suspiciousAccountTransfersRepository.findAll()).thenReturn(suspiciousAccountTransfersList);
        lenient().when(suspiciousAccountTransfersRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(suspiciousAccountTransfers));
    }

    @Test
    public void createTest() {
        SuspiciousAccountTransfersDTO result = suspiciousAccountTransfersService.add(suspiciousAccountTransfersDTO);
        assertNotNull(result);

        verify(suspiciousAccountTransfersMapper).toEntity(suspiciousAccountTransfersDTO);
        verify(suspiciousAccountTransfersMapper).toDto(suspiciousAccountTransfers);
        verify(suspiciousAccountTransfersRepository).save(suspiciousAccountTransfers);
    }

    @Test
    public void getAllTest() {
        when(suspiciousAccountTransfersRepository.findAll()).thenReturn(suspiciousAccountTransfersList);
        when(suspiciousAccountTransfersMapper.toDtoList(suspiciousAccountTransfersList)).thenReturn(suspiciousAccountTransfersDTOList);

        List<SuspiciousAccountTransfersDTO> result = suspiciousAccountTransfersService.getAll();
        assertNotNull(result);
        assertEquals(suspiciousAccountTransfersDTOList, result);
        verify(suspiciousAccountTransfersRepository).findAll();
        verify(suspiciousAccountTransfersMapper).toDtoList(suspiciousAccountTransfersList);
    }

    @Test
    public void getByIdSuccessfulTest() {
        SuspiciousAccountTransfersDTO result = suspiciousAccountTransfersService.getById(1L);

        assertNotNull(result);
        assertEquals(suspiciousAccountTransfersDTO, result);
        verify(suspiciousAccountTransfersRepository).findById(1L);
        verify(suspiciousAccountTransfersMapper).toDto(suspiciousAccountTransfers);
    }

    @Test
    public void getByIdThrowExceptionTest() {
        when(suspiciousAccountTransfersRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> suspiciousAccountTransfersService.getById(1L));

        verify(suspiciousAccountTransfersRepository).findById(1L);
    }

    @Test
    public void updateSuccessfulTest() {
        SuspiciousAccountTransfersDTO result = suspiciousAccountTransfersService.update(1L, suspiciousAccountTransfersDTO);
        assertNotNull(result);
        assertEquals(suspiciousAccountTransfersDTO, result);
    }

    @Test
    public void updateThrowExceptionTest() {
        when(suspiciousAccountTransfersRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> suspiciousAccountTransfersService.update(1L, suspiciousAccountTransfersDTO));

        verify(suspiciousAccountTransfersRepository).findById(1L);
        verify(suspiciousAccountTransfersRepository, never()).save(any(SuspiciousAccountTransfers.class));
        verify(suspiciousAccountTransfersMapper, never()).toEntity(any(SuspiciousAccountTransfersDTO.class));
        verify(suspiciousAccountTransfersMapper, never()).toDto(any(SuspiciousAccountTransfers.class));
    }

    @Test
    public void deleteSuccessfulTest() {
        when(suspiciousAccountTransfersRepository.findById(1L)).thenReturn(Optional.of(suspiciousAccountTransfers));
        suspiciousAccountTransfersService.delete(1L);
        verify(suspiciousAccountTransfersRepository).deleteById(1L);
    }

    @Test
    public void deleteThrowExceptionTest() {
        when(suspiciousAccountTransfersRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> suspiciousAccountTransfersService.delete(1L));
        verify(suspiciousAccountTransfersRepository).findById(1L);
    }

}