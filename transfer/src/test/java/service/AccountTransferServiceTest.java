package service;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.serviceImpl.AccountTransferServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountTransferServiceTest {

    @Mock(lenient = true)
    private AccountTransferRepository accountTransferRepository;

    @Mock(lenient = true)
    private AccountTransferMapper accountTransferMapper;

    @InjectMocks
    private AccountTransferServiceImpl accountTransferService;

    private AccountTransferDTO accountTransferDTO = new AccountTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private AccountTransferDTO accountTransferDTO2 = new AccountTransferDTO(0L, 3L, new BigDecimal(3), "hello", 4L);
    private AccountTransferDTO accountTransferDTO3 = new AccountTransferDTO(1L, 3L, new BigDecimal(3), "hello", 4L);
    private AccountTransferDTO accountTransferDTO4 = new AccountTransferDTO(2L, 3L, new BigDecimal(3), "hello", 4L);
    private AccountTransfer accountTransfer = new AccountTransfer(1L, 2L, new BigDecimal(3), "hello", 4L);
    private AccountTransfer accountTransfer2 = new AccountTransfer(1L, 3L, new BigDecimal(3), "hello", 4L);

    @Test
    public void getAllTest() {
        doReturn(accountTransfer).when(accountTransferMapper).toEntity(accountTransferDTO);
        doReturn(accountTransferDTO).when(accountTransferMapper).toDto(accountTransfer);
        accountTransferService.add(accountTransferDTO);
        accountTransferService.add(accountTransferDTO4);
        accountTransferService.all();
        verify(accountTransferRepository).findAll();

    }

    @Test
    public void addTest() {

        doReturn(accountTransfer).when(accountTransferMapper).toEntity(accountTransferDTO);
        doReturn(accountTransferDTO).when(accountTransferMapper).toDto(accountTransfer);
        accountTransferService.add(accountTransferDTO);

        verify(accountTransferRepository).save(accountTransfer);
    }

    @Test
    public void ifDtoInvalidTest() {
        assertThrows(Exception.class, () -> new AccountTransferServiceImpl().add(accountTransferDTO2));
    }


    @Test
    public void removeUserByIdTest() {
        doReturn(accountTransfer).when(accountTransferMapper).toEntity(accountTransferDTO);

        accountTransferService.add(accountTransferDTO);
        accountTransferService.delete(1L);
        verify(accountTransferRepository).deleteById(accountTransfer.getId());

    }

    @Test
    public void ifIdInvalidTest() {
        assertThrows(Exception.class, () -> new AccountTransferServiceImpl().delete(accountTransferDTO2.getId()));
    }


    @Test
    public void updateTest() {
        doReturn(accountTransfer2).when(accountTransferMapper).toEntity(accountTransferDTO3);
        doReturn(accountTransferDTO).when(accountTransferMapper).toDto(accountTransfer);

        accountTransferService.add(accountTransferDTO);
        accountTransferService.add(accountTransferDTO3);

        accountTransferService.update(accountTransferDTO3);
        verify(accountTransferRepository).save(accountTransferMapper.toEntity(accountTransferDTO3));
    }


    @Test
    public void showByIdTest() {

        doReturn(accountTransfer).when(accountTransferMapper).toEntity(accountTransferDTO);
        doReturn(accountTransferDTO).when(accountTransferMapper).toDto(accountTransfer);
        accountTransferService.add(accountTransferDTO);

        when(accountTransferRepository.findById(accountTransfer.getId())).thenReturn(of(accountTransfer));

        AccountTransferDTO expectedPhoneTransfer = new AccountTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);

        assertEquals(expectedPhoneTransfer, accountTransferService.showById(accountTransferDTO.getId()));

        verify(accountTransferRepository).findById(accountTransfer.getId());
    }

}
