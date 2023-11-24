package service;

import com.bank.transfer.dto.TransferAuditDTO;
import com.bank.transfer.entity.TransferAudit;
import com.bank.transfer.mapper.TransferAuditMapper;
import com.bank.transfer.repository.TransferAuditRepository;
import com.bank.transfer.serviceImpl.TransferAuditServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferAuditServiceTest {

    @Mock(lenient = true)
    private TransferAuditRepository transferAuditRepository;

    @Mock(lenient = true)
    private TransferAuditMapper transferAuditMapper;

    @InjectMocks
    private TransferAuditServiceImpl transferAuditService;


    private TransferAuditDTO transferAuditDTO = new TransferAuditDTO(1L, "hello", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");

    private TransferAuditDTO transferAuditDTO2 = new TransferAuditDTO(0L, "goodbye", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");

    private TransferAuditDTO transferAuditDTO3 = new TransferAuditDTO(1L, "goodbye", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");
    private TransferAuditDTO transferAuditDTO4 = new TransferAuditDTO(2L, "goodbye", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");
    private TransferAudit transferAudit = new TransferAudit(1L, "hello", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");
    private TransferAudit transferAudit2 = new TransferAudit(1L, "goodbye", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");

    @Test
    public void getAllTest() {
        doReturn(transferAudit).when(transferAuditMapper).toEntity(transferAuditDTO);
        doReturn(transferAuditDTO).when(transferAuditMapper).toDto(transferAudit);
        transferAuditService.add(transferAuditDTO);
        transferAuditService.add(transferAuditDTO4);
        transferAuditService.all();
        verify(transferAuditRepository).findAll();

    }

    @Test
    public void addTest() {

        doReturn(transferAudit).when(transferAuditMapper).toEntity(transferAuditDTO);
        doReturn(transferAuditDTO).when(transferAuditMapper).toDto(transferAudit);
        transferAuditService.add(transferAuditDTO);

        verify(transferAuditRepository).save(transferAudit);
    }

    @Test
    public void ifDtoInvalidTest() {
        assertThrows(Exception.class, () -> new TransferAuditServiceImpl().add(transferAuditDTO2));
    }


    @Test
    public void removeUserByIdTest() {
        doReturn(transferAudit).when(transferAuditMapper).toEntity(transferAuditDTO);

        transferAuditService.add(transferAuditDTO);
        transferAuditService.delete(1L);
        verify(transferAuditRepository).deleteById(transferAudit.getId());

    }

    @Test
    public void ifIdInvalidTest() {
        assertThrows(Exception.class, () -> new TransferAuditServiceImpl().delete(transferAuditDTO2.getId()));
    }


    @Test
    public void updateTest() {
        doReturn(transferAudit2).when(transferAuditMapper).toEntity(transferAuditDTO3);
        doReturn(transferAuditDTO).when(transferAuditMapper).toDto(transferAudit);

        transferAuditService.add(transferAuditDTO);
        transferAuditService.add(transferAuditDTO3);

        transferAuditService.update(transferAuditDTO3);
        verify(transferAuditRepository).save(transferAuditMapper.toEntity(transferAuditDTO3));

    }


    @Test
    public void showByIdTest() {

        doReturn(transferAudit).when(transferAuditMapper).toEntity(transferAuditDTO);


        doReturn(transferAuditDTO).when(transferAuditMapper).toDto(transferAudit);
        transferAuditService.add(transferAuditDTO);

        when(transferAuditRepository.findById(transferAudit.getId())).thenReturn(java.util.Optional.of(transferAudit));

        TransferAuditDTO expectedPhoneTransfer = new TransferAuditDTO(1L, "hello", "world", "John", "Brown", LocalDateTime.of(2012, Month.DECEMBER, 6, 12, 20), LocalDateTime.of(2020, Month.MAY, 23, 15, 33), "newJson", "json");

        assertEquals(expectedPhoneTransfer, transferAuditService.showById(transferAuditDTO.getId()));

        verify(transferAuditRepository).findById(transferAudit.getId());

    }

}
