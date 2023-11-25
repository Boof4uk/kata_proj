package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.mapper.AuditMapper;
import com.bank.publicinfo.repository.AuditRepository;
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
class AuditServiceImplTest {

    @Mock
    private AuditMapper auditMapper;
    @Mock
    private AuditRepository auditRepository;
    @InjectMocks
    private AuditServiceImpl auditService;

    private Audit audit;
    private AuditDTO auditDTO;

    @BeforeEach
    public void setUp() {
        audit = new Audit(1L,
                "тип сущности",
                "тип операции",
                "кто создал",
                "кто изменил",
                LocalDateTime.of(2023, 11, 14, 20, 36),
                LocalDateTime.of(2023, 11, 14, 20, 36),
                "json, заполняется при изменении",
                "json, заполняется при изменении и сохранении");
        auditDTO = new AuditDTO(1L,
                "тип сущности",
                "тип операции",
                "кто создал",
                "кто изменил",
                "2023-11-14T20:36:36",
                "2023-11-14T20:36:36",
                "json, заполняется при изменении",
                "json, заполняется при изменении и сохранении");

    }

    /*
    Проверка получения всех записей.
    Создаем лист из audit
    Мокаем auditRepository, чтобы возвращал лист из объектов,
    запускаем сервис
    сравниваем размеры листов
    проверяем что audit маппер вызвался для всех объектов
     */
    @Test
    public void getAllTest() {
        List<Audit> auditList = List.of(audit);
        when(auditRepository.findAll()).thenReturn(auditList);

        List<AuditDTO> actualResult = auditService.getAll();

        assertEquals(actualResult.size(), auditList.size());
        verify(auditMapper, times(auditList.size())).entityToDto(any(Audit.class));
    }

    /*
    Подготовка:
       маппер на audit должен вернуть auditDTO
    Действие:
        сохраняем auditDto
    Проверка:
        один раз вызвался auditRepository на объект audit
        один раз вызвался auditMapper на объект auditDto
     */
    @Test
    public void saveTest() {
        doReturn(audit).when(auditMapper).dtoToEntity(auditDTO);

        auditService.save(auditDTO);

        verify(auditRepository).save(audit);
        verify(auditMapper).dtoToEntity(auditDTO);
    }

    @Test
    public void findTest() {
        Long id = 1L;
        when(auditRepository.findById(id)).thenReturn(Optional.of(audit));
        when(auditMapper.entityToDto(audit)).thenReturn(auditDTO);

        AuditDTO actualResult = auditService.find(id);

        assertNotNull(actualResult);
        verify(auditRepository).findById(id);
        verify(auditMapper).entityToDto(audit);
        assertThat(actualResult).isEqualTo(auditDTO);
    }

    @Test
    public void findIsNotValidTest() {
        Long id = 111L;
        when(auditRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> auditService.find(id));
    }

    @Test
    public void updateTest() {
        doReturn(audit).when(auditMapper).dtoToEntity(auditDTO);
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));

        auditService.update(auditDTO);

        verify(auditRepository).save(audit);
        verify(auditMapper).dtoToEntity(auditDTO);
    }

    @Test
    public void updateNotValidTest() {
        when(auditRepository.findById(auditDTO.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> auditService.update(auditDTO));
    }


    @Test
    public void deleteTest() {
        Long validId = 1L;
        when(auditRepository.findById(validId)).thenReturn(Optional.of(audit));

        auditService.delete(validId);

        verify(auditRepository).deleteById(audit.getId());
    }

    @Test
    public void deleteNotValidTest() {
        Long notValid = 10L;
        when(auditRepository.findById(notValid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> auditService.delete(notValid));
    }
}