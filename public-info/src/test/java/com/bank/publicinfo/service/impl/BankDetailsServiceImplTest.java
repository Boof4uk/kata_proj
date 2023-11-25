package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankDetailsServiceImplTest {

    @Mock
    private BankDetailsMapper bankDetailsMapper;
    @Mock
    private BankDetailsRepository bankDetailsRepository;
    @InjectMocks
    private BankDetailsServiceImpl bankDetailsService;

    private BankDetails bankDetails;
    private BankDetailsDTO bankDetailsDTO;

    @BeforeEach
    public void setUp() {
        bankDetails = new BankDetails(1L,
                100L,
                200L,
                300L,
                400,
                "city",
                "company",
                "name");
        bankDetailsDTO = new BankDetailsDTO(1L,
                200L,
                222L,
                333L,
                444,
                "city2",
                "company2",
                "name2");
    }
    /*
    Проверка получения всех записей.
    Создаем лист из bankDetails
    Мокаем bankDetailsRepository, чтобы возвращал лист из объектов,
    запускаем сервис
    сравниваем размеры листов
    проверяем что bankDetails маппер вызвался для всех объектов
     */
    @Test
    void getAllTest() {
        List<BankDetails> bankDetailsList = List.of(bankDetails);
        when(bankDetailsRepository.findAll()).thenReturn(bankDetailsList);

        List<BankDetailsDTO> actualResult = bankDetailsService.getAll();

        assertEquals(actualResult.size(), bankDetailsList.size());
        verify(bankDetailsMapper, times(bankDetailsList.size())).entityToDto(any(BankDetails.class));
    }

    /*
    Подготовка:
       маппер на bankDetails должен вернуть bankDetailsDTO
    Действие:
        сохраняем bankDetailsDto
    Проверка:
        один раз вызвался bankDetailsRepository на объект bankDetails
        один раз вызвался bankDetailsMapper на объект bankDetailsDto
     */
    @Test
    void saveTest() {
        doReturn(bankDetails).when(bankDetailsMapper).dtoToEntity(bankDetailsDTO);

        bankDetailsService.save(bankDetailsDTO);

        verify(bankDetailsRepository).save(bankDetails);
        verify(bankDetailsMapper).dtoToEntity(bankDetailsDTO);
    }

    @Test
    void findTest() {
        Long id = 1L;
        when(bankDetailsRepository.findById(id)).thenReturn(Optional.of(bankDetails));
        when(bankDetailsMapper.entityToDto(bankDetails)).thenReturn(bankDetailsDTO);

        BankDetailsDTO actualResult = bankDetailsService.find(id);

        assertNotNull(actualResult);
        verify(bankDetailsRepository).findById(id);
        verify(bankDetailsMapper).entityToDto(bankDetails);
        assertThat(actualResult).isEqualTo(bankDetailsDTO);
    }

    @Test
    void findIsNotValidTest() {
        Long id = 111L;
        when(bankDetailsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bankDetailsService.find(id));
    }

    @Test
    void updateTest() {
        doReturn(bankDetails).when(bankDetailsMapper).dtoToEntity(bankDetailsDTO);
        when(bankDetailsRepository.findById(1L)).thenReturn(Optional.of(bankDetails));

        bankDetailsService.update(bankDetailsDTO);

        verify(bankDetailsRepository).save(bankDetails);
        verify(bankDetailsMapper).dtoToEntity(bankDetailsDTO);
    }

    @Test
    void updateNotValidTest() {
        when(bankDetailsRepository.findById(bankDetailsDTO.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bankDetailsService.update(bankDetailsDTO));
    }


    @Test
    void deleteTest() {
        Long validId = 1L;
        when(bankDetailsRepository.findById(validId)).thenReturn(Optional.of(bankDetails));

        bankDetailsService.delete(validId);

        verify(bankDetailsRepository).deleteById(bankDetails.getId());
    }

    @Test
    void deleteNotValidTest() {
        Long notValid = 10L;
        when(bankDetailsRepository.findById(notValid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bankDetailsService.delete(notValid));
    }
}