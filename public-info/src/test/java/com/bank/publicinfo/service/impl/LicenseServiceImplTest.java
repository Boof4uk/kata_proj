package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.repository.LicenseRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LicenseServiceImplTest {

    @Mock
    private LicenseMapper licenseMapper;
    @Mock
    private LicenseRepository licenseRepository;
    @InjectMocks
    private LicenseServiceImpl licenseService;

    private License license;
    private LicenseDTO licenseDTO;

    @BeforeEach
    public void setUp() {
        license = new License(1L,new byte[1],new BankDetails());
        licenseDTO = new LicenseDTO(1L,new byte[1],new BankDetailsDTO());
    }

    /*
    Проверка получения всех записей.
    Создаем лист из license
    Мокаем licenseRepository, чтобы возвращал лист из объектов,
    запускаем сервис
    сравниваем размеры листов
    проверяем что license маппер вызвался для всех объектов
     */
    @Test
    public void getAllTest() {
        List<License> licenseList = List.of(license);
        when(licenseRepository.findAll()).thenReturn(licenseList);

        List<LicenseDTO> actualResult = licenseService.getAll();

        assertEquals(actualResult.size(), licenseList.size());
        verify(licenseMapper, times(licenseList.size())).entityToDto(any(License.class));
    }

    /*
    Подготовка:
       маппер на license должен вернуть licenseDTO
    Действие:
        сохраняем licenseDto
    Проверка:
        один раз вызвался licenseRepository на объект license
        один раз вызвался licenseMapper на объект licenseDto
     */
    @Test
    public void saveTest() {
        doReturn(license).when(licenseMapper).dtoToEntity(licenseDTO);

        licenseService.save(licenseDTO);

        verify(licenseRepository).save(license);
        verify(licenseMapper).dtoToEntity(licenseDTO);
    }

    @Test
    public void findTest() {
        Long id = 1L;
        when(licenseRepository.findById(id)).thenReturn(Optional.of(license));
        when(licenseMapper.entityToDto(license)).thenReturn(licenseDTO);

        LicenseDTO actualResult = licenseService.find(id);

        assertNotNull(actualResult);
        verify(licenseRepository).findById(id);
        verify(licenseMapper).entityToDto(license);
        assertThat(actualResult).isEqualTo(licenseDTO);
    }

    @Test
    public void findIsNotValidTest() {
        Long id = 111L;
        when(licenseRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> licenseService.find(id));
    }

    @Test
    public void updateTest() {
        doReturn(license).when(licenseMapper).dtoToEntity(licenseDTO);
        when(licenseRepository.findById(1L)).thenReturn(Optional.of(license));

        licenseService.update(licenseDTO);

        verify(licenseRepository).save(license);
        verify(licenseMapper).dtoToEntity(licenseDTO);
    }

    @Test
    public void updateNotValidTest() {
        when(licenseRepository.findById(licenseDTO.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> licenseService.update(licenseDTO));
    }


    @Test
    public void deleteTest() {
        Long validId = 1L;
        when(licenseRepository.findById(validId)).thenReturn(Optional.of(license));

        licenseService.delete(validId);

        verify(licenseRepository).deleteById(license.getId());
    }

    @Test
    public void deleteNotValidTest() {
        Long notValid = 10L;
        when(licenseRepository.findById(notValid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> licenseService.delete(notValid));
    }
}