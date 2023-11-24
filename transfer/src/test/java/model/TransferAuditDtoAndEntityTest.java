package model;

import com.bank.transfer.dto.TransferAuditDTO;
import com.bank.transfer.entity.TransferAudit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class TransferAuditDtoAndEntityTest {

    private TransferAuditDTO dto = new TransferAuditDTO(1L, "hello", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");
    private TransferAudit entity = new TransferAudit(1L, "hello", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");


    @Test
    public void dtoTest() {
        TransferAuditDTO accountTransferDTO = new TransferAuditDTO();
        TransferAudit accountTransfer = new TransferAudit();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getEntityType(), entity.getEntityType());
        assertEquals(dto.getOperationType(), entity.getOperationType());
        assertEquals(dto.getCreatedAt(), entity.getCreatedAt());
        assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
        assertEquals(dto.getModifiedAt(), entity.getModifiedAt());
        assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
        assertEquals(dto.getNewEntityJson(), entity.getNewEntityJson());
        assertEquals(dto.getEntityJson(), entity.getEntityJson());


        TransferAuditDTO transferDTO = new TransferAuditDTO(entity.getId(), entity.getEntityType(), entity.getOperationType(), entity.getCreatedBy(), entity.getModifiedBy(), entity.getCreatedAt(), entity.getModifiedAt(), entity.getNewEntityJson(), entity.getEntityJson());
        assertEquals(transferDTO, dto);
        assertEquals(transferDTO.toString(), dto.toString());


        TransferAudit transfer = new TransferAudit(dto.getId(), dto.getEntityType(), dto.getOperationType(), dto.getCreatedBy(), dto.getModifiedBy(), dto.getCreatedAt(), dto.getModifiedAt(), dto.getNewEntityJson(), dto.getEntityJson());
        assertEquals(transfer, entity);
        assertEquals(transfer.toString(), entity.toString());

        accountTransfer.setId(entity.getId());
        accountTransfer.setEntityJson(entity.getEntityJson());
        accountTransfer.setEntityType(entity.getEntityType());
        accountTransfer.setModifiedAt(entity.getModifiedAt());
        accountTransfer.setModifiedBy(entity.getModifiedBy());
        accountTransfer.setNewEntityJson(entity.getNewEntityJson());
        accountTransfer.setOperationType(entity.getOperationType());
        accountTransfer.setCreatedAt(entity.getCreatedAt());
        accountTransfer.setCreatedBy(entity.getCreatedBy());


        accountTransferDTO.setId(dto.getId());
        accountTransferDTO.setEntityJson(dto.getEntityJson());
        accountTransferDTO.setEntityType(dto.getEntityType());
        accountTransferDTO.setModifiedAt(dto.getModifiedAt());
        accountTransferDTO.setModifiedBy(dto.getModifiedBy());
        accountTransferDTO.setNewEntityJson(dto.getNewEntityJson());
        accountTransferDTO.setOperationType(dto.getOperationType());
        accountTransferDTO.setCreatedAt(dto.getCreatedAt());
        accountTransferDTO.setCreatedBy(dto.getCreatedBy());


        assertEquals(dto.hashCode(), transferDTO.hashCode());
        assertEquals(entity.hashCode(), transfer.hashCode());

        assertNotEquals(dto, entity);

    }

}