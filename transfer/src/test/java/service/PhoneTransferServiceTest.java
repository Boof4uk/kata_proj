package service;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
import com.bank.transfer.serviceImpl.PhoneTransferServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneTransferServiceTest {

    @Mock(lenient = true)
    private PhoneTransferRepository phoneTransferRepository;

    @Mock(lenient = true)
    private PhoneTransferMapper phoneTransferMapper;

    @InjectMocks
    private PhoneTransferServiceImpl phoneTransferService;


    private PhoneTransferDTO phoneTransferDTO = new PhoneTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private PhoneTransferDTO phoneTransferDTO2 = new PhoneTransferDTO(0L, 3L, new BigDecimal(3), "hello", 4L);

    private PhoneTransferDTO phoneTransferDTO3 = new PhoneTransferDTO(1L, 3L, new BigDecimal(3), "hello", 4L);
    private PhoneTransferDTO phoneTransferDTO4 = new PhoneTransferDTO(2L, 3L, new BigDecimal(3), "hello", 4L);
    private PhoneTransfer phoneTransfer = new PhoneTransfer(1L, 2L, new BigDecimal(3), "hello", 4L);
    private PhoneTransfer phoneTransfer2 = new PhoneTransfer(1L, 3L, new BigDecimal(3), "hello", 4L);

    @Test
    public void getAllTest() {
        doReturn(phoneTransfer).when(phoneTransferMapper).toEntity(phoneTransferDTO);
        doReturn(phoneTransferDTO).when(phoneTransferMapper).toDto(phoneTransfer);
        phoneTransferService.add(phoneTransferDTO);
        phoneTransferService.add(phoneTransferDTO4);
        phoneTransferService.all();
        verify(phoneTransferRepository).findAll();

    }

    @Test
    public void addTest() {

        doReturn(phoneTransfer).when(phoneTransferMapper).toEntity(phoneTransferDTO);
        doReturn(phoneTransferDTO).when(phoneTransferMapper).toDto(phoneTransfer);
        phoneTransferService.add(phoneTransferDTO);

        verify(phoneTransferRepository).save(phoneTransfer);
    }

    @Test
    public void ifDtoInvalidTest() {
        assertThrows(Exception.class, () -> new PhoneTransferServiceImpl().add(phoneTransferDTO2));
    }


    @Test
    public void removeUserByIdTest() {
        doReturn(phoneTransfer).when(phoneTransferMapper).toEntity(phoneTransferDTO);

        phoneTransferService.add(phoneTransferDTO);
        phoneTransferService.delete(1L);
        verify(phoneTransferRepository).deleteById(phoneTransfer.getId());

    }

    @Test
    public void ifIdInvalidTest() {
        assertThrows(Exception.class, () -> new PhoneTransferServiceImpl().delete(phoneTransferDTO2.getId()));
    }


    @Test
    public void updateTest() {
        doReturn(phoneTransfer2).when(phoneTransferMapper).toEntity(phoneTransferDTO3);
        doReturn(phoneTransferDTO).when(phoneTransferMapper).toDto(phoneTransfer);

        phoneTransferService.add(phoneTransferDTO);
        phoneTransferService.add(phoneTransferDTO3);

        phoneTransferService.update(phoneTransferDTO3);
        verify(phoneTransferRepository).save(phoneTransferMapper.toEntity(phoneTransferDTO3));
    }


    @Test
    public void showByIdTest() {

        doReturn(phoneTransfer).when(phoneTransferMapper).toEntity(phoneTransferDTO);


        doReturn(phoneTransferDTO).when(phoneTransferMapper).toDto(phoneTransfer);
        phoneTransferService.add(phoneTransferDTO);

        when(phoneTransferRepository.findById(phoneTransfer.getId())).thenReturn(java.util.Optional.of(phoneTransfer));

        PhoneTransferDTO expectedPhoneTransfer = new PhoneTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);

        assertEquals(expectedPhoneTransfer, phoneTransferService.showById(phoneTransferDTO.getId()));

        verify(phoneTransferRepository).findById(phoneTransfer.getId());

    }

}
