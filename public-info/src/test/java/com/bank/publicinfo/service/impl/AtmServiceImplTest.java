package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.repository.AtmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
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
class AtmServiceImplTest {

    @Mock
    private AtmMapper atmMapper;
    @Mock
    private AtmRepository atmRepository;
    @InjectMocks
    private AtmServiceImpl atmService;

    private Atm atm;
    private AtmDTO atmDTO;

    @BeforeEach
    public void setUp() {
        atmDTO = new AtmDTO(1L,
                "адрес",
                LocalTime.of(10,0),
                LocalTime.of(20,0),
                true,
                null);
        atm = new Atm(1L,
                "адрес",
                LocalTime.of(10,0),
                LocalTime.of(20,0),
                true,
                null);
    }

    /*
    Проверка получения всех записей.
    Создаем лист из atm
    Мокаем atmRepository, чтобы возвращал лист из объектов,
    запускаем сервис
    сравниваем размеры листов
    проверяем что atm маппер вызвался для всех объектов
     */
    @Test
    void getAllTest() {
        List<Atm> atmList = List.of(atm);
        when(atmRepository.findAll()).thenReturn(atmList);

        List<AtmDTO> actualResult = atmService.getAll();

        assertEquals(actualResult.size(), atmList.size());
        verify(atmMapper, times(atmList.size())).entityToDto(any(Atm.class));
    }

    /*
    Подготовка:
       маппер на atm должен вернуть atmDTO
    Действие:
        сохраняем atmDto
    Проверка:
        один раз вызвался atmRepository на объект atm
        один раз вызвался atmMapper на объект atmDto
     */
    @Test
    void saveTest() {
        doReturn(atm).when(atmMapper).dtoToEntity(atmDTO);

        atmService.save(atmDTO);

        verify(atmRepository).save(atm);
        verify(atmMapper).dtoToEntity(atmDTO);
    }

    @Test
    void findTest() {
        Long id = 1L;
        when(atmRepository.findById(id)).thenReturn(Optional.of(atm));
        when(atmMapper.entityToDto(atm)).thenReturn(atmDTO);

        AtmDTO actualResult = atmService.find(id);

        assertNotNull(actualResult);
        verify(atmRepository).findById(id);
        verify(atmMapper).entityToDto(atm);
        assertThat(actualResult).isEqualTo(atmDTO);
    }

    @Test
    void findIsNotValidTest() {
        Long id = 111L;
        when(atmRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> atmService.find(id));
    }

    @Test
    void updateTest() {
        doReturn(atm).when(atmMapper).dtoToEntity(atmDTO);
        when(atmRepository.findById(1L)).thenReturn(Optional.of(atm));

        atmService.update(atmDTO);

        verify(atmRepository).save(atm);
        verify(atmMapper).dtoToEntity(atmDTO);
    }

    @Test
    void updateNotValidTest() {
        when(atmRepository.findById(atmDTO.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> atmService.update(atmDTO));
    }


    @Test
    void deleteTest() {
        Long validId = 1L;
        when(atmRepository.findById(validId)).thenReturn(Optional.of(atm));

        atmService.delete(validId);

        verify(atmRepository).deleteById(atm.getId());
    }

    @Test
    void deleteNotValidTest() {
        Long notValid = 10L;
        when(atmRepository.findById(notValid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> atmService.delete(notValid));
    }
}