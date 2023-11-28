package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDTO;
import com.bank.antifraud.entity.SuspiciousPhoneTransfers;
import com.bank.antifraud.exception.EntityNotFoundException;
import com.bank.antifraud.mapper.SuspiciousPhoneTransfersMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransfersRepository;
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
class SuspiciousPhoneTransfersServiceImplTest {
    @InjectMocks
    private SuspiciousPhoneTransfersServiceImpl suspiciousPhoneTransfersService;
    @Mock(lenient = true)
    private SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository;
    @Mock(lenient = true)
    private SuspiciousPhoneTransfersMapper suspiciousPhoneTransfersMapper;
    @Mock
    private SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO;
    private SuspiciousPhoneTransfers suspiciousPhoneTransfers;
    private List<SuspiciousPhoneTransfers> suspiciousPhoneTransfersList = new ArrayList<>();
    private List<SuspiciousPhoneTransfersDTO> suspiciousPhoneTransfersDTOList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        suspiciousPhoneTransfers = new SuspiciousPhoneTransfers(1L, 1L,
                true, true, "string", "string");
        suspiciousPhoneTransfersDTO = new SuspiciousPhoneTransfersDTO(1L, 1L,
                true, true, "string", "string");
        suspiciousPhoneTransfersList.add(suspiciousPhoneTransfers);
        suspiciousPhoneTransfersDTOList.add(suspiciousPhoneTransfersDTO);

        lenient().when(suspiciousPhoneTransfersMapper.toEntity(any())).thenReturn(suspiciousPhoneTransfers);
        lenient().when(suspiciousPhoneTransfersMapper.toDto(any())).thenReturn(suspiciousPhoneTransfersDTO);
        lenient().when(suspiciousPhoneTransfersRepository.save(any())).thenReturn(suspiciousPhoneTransfers);
        lenient().when(suspiciousPhoneTransfersRepository.findAll()).thenReturn(suspiciousPhoneTransfersList);
        lenient().when(suspiciousPhoneTransfersRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(suspiciousPhoneTransfers));
    }

    @Test
    public void createTest() {
        SuspiciousPhoneTransfersDTO result = suspiciousPhoneTransfersService.add(suspiciousPhoneTransfersDTO);
        assertNotNull(result);

        verify(suspiciousPhoneTransfersMapper).toEntity(suspiciousPhoneTransfersDTO);
        verify(suspiciousPhoneTransfersMapper).toDto(suspiciousPhoneTransfers);
        verify(suspiciousPhoneTransfersRepository).save(suspiciousPhoneTransfers);
    }

    @Test
    public void getAllTest() {
        when(suspiciousPhoneTransfersRepository.findAll()).thenReturn(suspiciousPhoneTransfersList);
        when(suspiciousPhoneTransfersMapper.toDtoList(suspiciousPhoneTransfersList)).thenReturn(suspiciousPhoneTransfersDTOList);

        List<SuspiciousPhoneTransfersDTO> result = suspiciousPhoneTransfersService.getAll();
        assertNotNull(result);
        assertEquals(suspiciousPhoneTransfersDTOList, result);
        verify(suspiciousPhoneTransfersRepository).findAll();
        verify(suspiciousPhoneTransfersMapper).toDtoList(suspiciousPhoneTransfersList);
    }

    @Test
    public void getByIdSuccessfulTest() {
        SuspiciousPhoneTransfersDTO result = suspiciousPhoneTransfersService.getById(1L);

        assertNotNull(result);
        assertEquals(suspiciousPhoneTransfersDTO, result);
        verify(suspiciousPhoneTransfersRepository).findById(1L);
        verify(suspiciousPhoneTransfersMapper).toDto(suspiciousPhoneTransfers);
    }

    @Test
    public void getByIdThrowExceptionTest() {
        when(suspiciousPhoneTransfersRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> suspiciousPhoneTransfersService.getById(1L));

        verify(suspiciousPhoneTransfersRepository).findById(1L);
    }

    @Test
    public void updateSuccessfulTest() {
        SuspiciousPhoneTransfersDTO result = suspiciousPhoneTransfersService.update(1L, suspiciousPhoneTransfersDTO);
        assertNotNull(result);
        assertEquals(suspiciousPhoneTransfersDTO, result);
    }

    @Test
    public void updateThrowExceptionTest() {
        when(suspiciousPhoneTransfersRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> suspiciousPhoneTransfersService.update(1L, suspiciousPhoneTransfersDTO));

        verify(suspiciousPhoneTransfersRepository).findById(1L);
        verify(suspiciousPhoneTransfersRepository, never()).save(any(SuspiciousPhoneTransfers.class));
        verify(suspiciousPhoneTransfersMapper, never()).toEntity(any(SuspiciousPhoneTransfersDTO.class));
        verify(suspiciousPhoneTransfersMapper, never()).toDto(any(SuspiciousPhoneTransfers.class));
    }

    @Test
    public void deleteSuccessfulTest() {
        when(suspiciousPhoneTransfersRepository.findById(1L)).thenReturn(Optional.of(suspiciousPhoneTransfers));
        suspiciousPhoneTransfersService.delete(1L);
        verify(suspiciousPhoneTransfersRepository).deleteById(1L);
    }

    @Test
    public void deleteThrowExceptionTest() {
        when(suspiciousPhoneTransfersRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> suspiciousPhoneTransfersService.delete(1L));
        verify(suspiciousPhoneTransfersRepository).findById(1L);
    }


}