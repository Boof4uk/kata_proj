package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.repository.CertificateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
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
class CertificateServiceImplTest {

    @Mock
    private CertificateMapper certificateMapper;
    @Mock
    private CertificateRepository certificateRepository;
    @InjectMocks
    private CertificateServiceImpl certificateService;

    private Certificate certificate;
    private CertificateDTO certificateDTO;

    @BeforeEach
    public void setUp() {
        certificate = new Certificate(1L,new byte[1],new BankDetails());
        certificateDTO = new CertificateDTO(1L,new byte[1],new BankDetailsDTO());
    }

    /*
    Проверка получения всех записей.
    Создаем лист из certificate
    Мокаем certificateRepository, чтобы возвращал лист из объектов,
    запускаем сервис
    сравниваем размеры листов
    проверяем что certificate маппер вызвался для всех объектов
     */
    @Test
    public void getAllTest() {
        List<Certificate> certificateList = List.of(certificate);
        when(certificateRepository.findAll()).thenReturn(certificateList);

        List<CertificateDTO> actualResult = certificateService.getAll();

        assertEquals(actualResult.size(), certificateList.size());
        verify(certificateMapper, times(certificateList.size())).entityToDto(any(Certificate.class));
    }

    /*
    Подготовка:
       маппер на certificate должен вернуть certificateDTO
    Действие:
        сохраняем certificateDto
    Проверка:
        один раз вызвался certificateRepository на объект certificate
        один раз вызвался certificateMapper на объект certificateDto
     */
    @Test
    public void saveTest() {
        doReturn(certificate).when(certificateMapper).dtoToEntity(certificateDTO);

        certificateService.save(certificateDTO);

        verify(certificateRepository).save(certificate);
        verify(certificateMapper).dtoToEntity(certificateDTO);
    }

    @Test
    public void findTest() {
        Long id = 1L;
        when(certificateRepository.findById(id)).thenReturn(Optional.of(certificate));
        when(certificateMapper.entityToDto(certificate)).thenReturn(certificateDTO);

        CertificateDTO actualResult = certificateService.find(id);

        assertNotNull(actualResult);
        verify(certificateRepository).findById(id);
        verify(certificateMapper).entityToDto(certificate);
        assertThat(actualResult).isEqualTo(certificateDTO);
    }

    @Test
    public void findIsNotValidTest() {
        Long id = 111L;
        when(certificateRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> certificateService.find(id));
    }

    @Test
    public void updateTest() {
        doReturn(certificate).when(certificateMapper).dtoToEntity(certificateDTO);
        when(certificateRepository.findById(1L)).thenReturn(Optional.of(certificate));

        certificateService.update(certificateDTO);

        verify(certificateRepository).save(certificate);
        verify(certificateMapper).dtoToEntity(certificateDTO);
    }

    @Test
    public void updateNotValidTest() {
        when(certificateRepository.findById(certificateDTO.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> certificateService.update(certificateDTO));
    }


    @Test
    public void deleteTest() {
        Long validId = 1L;
        when(certificateRepository.findById(validId)).thenReturn(Optional.of(certificate));

        certificateService.delete(validId);

        verify(certificateRepository).deleteById(certificate.getId());
    }

    @Test
    public void deleteNotValidTest() {
        Long notValid = 10L;
        when(certificateRepository.findById(notValid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> certificateService.delete(notValid));
    }
}