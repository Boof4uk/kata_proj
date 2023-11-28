package com.bank.antifraud.model;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.entity.AntifraudAudit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AntifraudAuditDtoAndEntityTest {

    private AntifraudAudit entity = new AntifraudAudit(1L, "string", "string",
            "string", "string", LocalDateTime.of(2014, Month.DECEMBER, 6, 12, 20),
            LocalDateTime.of(2019, Month.MAY, 23, 15, 33), "newJson", "Json");
    private AntifraudAuditDTO dto = new AntifraudAuditDTO(1L, "string", "string",
            "string", "string", LocalDateTime.of(2014, Month.DECEMBER, 6, 12, 20),
            LocalDateTime.of(2019, Month.MAY, 23, 15, 33), "newJson", "Json");

    @Test
    public void dtoTest() {
        AntifraudAuditDTO antifraudAuditDTO = new AntifraudAuditDTO();
        AntifraudAudit antifraudAudit = new AntifraudAudit();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getEntityType(), entity.getEntityType());
        assertEquals(dto.getOperationType(), entity.getOperationType());
        assertEquals(dto.getCreatedAt(), entity.getCreatedAt());
        assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
        assertEquals(dto.getModifiedAt(), entity.getModifiedAt());
        assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
        assertEquals(dto.getNewEntityJson(), entity.getNewEntityJson());
        assertEquals(dto.getEntityJson(), entity.getEntityJson());


        AntifraudAuditDTO antifraudDTO = new AntifraudAuditDTO(entity.getId(), entity.getEntityType(), entity.getOperationType(), entity.getCreatedBy(), entity.getModifiedBy(), entity.getCreatedAt(), entity.getModifiedAt(), entity.getNewEntityJson(), entity.getEntityJson());
        assertEquals(antifraudDTO, dto);
        assertEquals(antifraudDTO.toString(), dto.toString());


        AntifraudAudit antifraud = new AntifraudAudit(dto.getId(), dto.getEntityType(), dto.getOperationType(), dto.getCreatedBy(), dto.getModifiedBy(), dto.getCreatedAt(), dto.getModifiedAt(), dto.getNewEntityJson(), dto.getEntityJson());
        assertEquals(antifraud, entity);
        assertEquals(antifraud.toString(), entity.toString());

        antifraudAudit.setId(entity.getId());
        antifraudAudit.setEntityJson(entity.getEntityJson());
        antifraudAudit.setEntityType(entity.getEntityType());
        antifraudAudit.setModifiedAt(entity.getModifiedAt());
        antifraudAudit.setModifiedBy(entity.getModifiedBy());
        antifraudAudit.setNewEntityJson(entity.getNewEntityJson());
        antifraudAudit.setOperationType(entity.getOperationType());
        antifraudAudit.setCreatedAt(entity.getCreatedAt());
        antifraudAudit.setCreatedBy(entity.getCreatedBy());


        antifraudAuditDTO.setId(dto.getId());
        antifraudAuditDTO.setEntityJson(dto.getEntityJson());
        antifraudAuditDTO.setEntityType(dto.getEntityType());
        antifraudAuditDTO.setModifiedAt(dto.getModifiedAt());
        antifraudAuditDTO.setModifiedBy(dto.getModifiedBy());
        antifraudAuditDTO.setNewEntityJson(dto.getNewEntityJson());
        antifraudAuditDTO.setOperationType(dto.getOperationType());
        antifraudAuditDTO.setCreatedAt(dto.getCreatedAt());
        antifraudAuditDTO.setCreatedBy(dto.getCreatedBy());


        assertEquals(dto.hashCode(), antifraudDTO.hashCode());
        assertEquals(entity.hashCode(), antifraud.hashCode());

    }

}
