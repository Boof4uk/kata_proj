package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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
class BranchServiceImplTest {

    @Mock
    private BranchMapper branchMapper;
    @Mock
    private BranchRepository branchRepository;
    @InjectMocks
    private BranchServiceImpl branchService;

    private Branch branch;
    private BranchDTO branchDTO;

    @BeforeEach
    public void setUp() {
        branch = new Branch(1L,
                "address",
                9999999L,
                "city",
                LocalTime.of(10,0),
                LocalTime.of(20,0));
        branchDTO = new BranchDTO(1L,
                "address",
                8888888888L,
                "city",
                LocalTime.of(11,0),
                LocalTime.of(21,0));
    }

    /*
    Проверка получения всех записей.
    Создаем лист из branch
    Мокаем branchRepository, чтобы возвращал лист из объектов,
    запускаем сервис
    сравниваем размеры листов
    проверяем что branch маппер вызвался для всех объектов
     */
    @Test
    public void getAllTest() {
        List<Branch> branchList = List.of(branch);
        when(branchRepository.findAll()).thenReturn(branchList);

        List<BranchDTO> actualResult = branchService.getAll();

        assertEquals(actualResult.size(), branchList.size());
        verify(branchMapper, times(branchList.size())).entityToDto(any(Branch.class));
    }

    /*
    Подготовка:
       маппер на branch должен вернуть branchDTO
    Действие:
        сохраняем branchDto
    Проверка:
        один раз вызвался branchRepository на объект branch
        один раз вызвался branchMapper на объект branchDto
     */
    @Test
    public void saveTest() {
        doReturn(branch).when(branchMapper).dtoToEntity(branchDTO);

        branchService.save(branchDTO);

        verify(branchRepository).save(branch);
        verify(branchMapper).dtoToEntity(branchDTO);
    }

    @Test
    public void findTest() {
        Long id = 1L;
        when(branchRepository.findById(id)).thenReturn(Optional.of(branch));
        when(branchMapper.entityToDto(branch)).thenReturn(branchDTO);

        BranchDTO actualResult = branchService.find(id);

        assertNotNull(actualResult);
        verify(branchRepository).findById(id);
        verify(branchMapper).entityToDto(branch);
        assertThat(actualResult).isEqualTo(branchDTO);
    }

    @Test
    public void findIsNotValidTest() {
        Long id = 111L;
        when(branchRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> branchService.find(id));
    }

    @Test
    public void updateTest() {
        doReturn(branch).when(branchMapper).dtoToEntity(branchDTO);
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        branchService.update(branchDTO);

        verify(branchRepository).save(branch);
        verify(branchMapper).dtoToEntity(branchDTO);
    }

    @Test
    public void updateNotValidTest() {
        when(branchRepository.findById(branchDTO.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> branchService.update(branchDTO));
    }


    @Test
    public void deleteTest() {
        Long validId = 1L;
        when(branchRepository.findById(validId)).thenReturn(Optional.of(branch));

        branchService.delete(validId);

        verify(branchRepository).deleteById(branch.getId());
    }

    @Test
    public void deleteNotValidTest() {
        Long notValid = 10L;
        when(branchRepository.findById(notValid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> branchService.delete(notValid));
    }
}