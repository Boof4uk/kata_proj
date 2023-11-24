package service;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.serviceImpl.CardTransferServiceImpl;
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
public class CardTransferServiceTest {

    @Mock(lenient = true)
    private CardTransferRepository cardTransferRepository;

    @Mock(lenient = true)
    private CardTransferMapper cardTransferMapper;

    @InjectMocks
    private CardTransferServiceImpl cardTransferService;

    private CardTransferDTO cardTransferDTO = new CardTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private CardTransferDTO cardTransferDTO2 = new CardTransferDTO(0L, 3L, new BigDecimal(3), "hello", 4L);
    private CardTransferDTO cardTransferDTO3 = new CardTransferDTO(1L, 3L, new BigDecimal(3), "hello", 4L);
    private CardTransferDTO cardTransferDTO4 = new CardTransferDTO(2L, 3L, new BigDecimal(3), "hello", 4L);
    private CardTransfer cardTransfer = new CardTransfer(1L, 2L, new BigDecimal(3), "hello", 4L);
    private CardTransfer cardTransfer2 = new CardTransfer(1L, 3L, new BigDecimal(3), "hello", 4L);


    @Test
    public void getAllTest() {
        doReturn(cardTransfer).when(cardTransferMapper).toEntity(cardTransferDTO);
        doReturn(cardTransferDTO).when(cardTransferMapper).toDto(cardTransfer);
        cardTransferService.add(cardTransferDTO);
        cardTransferService.add(cardTransferDTO4);
        cardTransferService.all();
        verify(cardTransferRepository).findAll();
    }

    @Test
    public void addTest() {

        doReturn(cardTransfer).when(cardTransferMapper).toEntity(cardTransferDTO);
        doReturn(cardTransferDTO).when(cardTransferMapper).toDto(cardTransfer);
        cardTransferService.add(cardTransferDTO);

        verify(cardTransferRepository).save(cardTransfer);
    }

    @Test
    public void ifDtoInvalidTest() {
        assertThrows(Exception.class, () -> new CardTransferServiceImpl().add(cardTransferDTO2));
    }


    @Test
    public void removeUserByIdTest() {
        doReturn(cardTransfer).when(cardTransferMapper).toEntity(cardTransferDTO);

        cardTransferService.add(cardTransferDTO);
        cardTransferService.delete(1L);
        verify(cardTransferRepository).deleteById(cardTransfer.getId());

    }

    @Test
    public void ifIdInvalidTest() {
        assertThrows(Exception.class, () -> new CardTransferServiceImpl().delete(cardTransferDTO2.getId()));
    }


    @Test
    public void updateTest() {
        doReturn(cardTransfer2).when(cardTransferMapper).toEntity(cardTransferDTO3);
        doReturn(cardTransferDTO).when(cardTransferMapper).toDto(cardTransfer);

        cardTransferService.add(cardTransferDTO);
        cardTransferService.add(cardTransferDTO3);

        cardTransferService.update(cardTransferDTO3);

        verify(cardTransferRepository).save(cardTransferMapper.toEntity(cardTransferDTO3));

    }


    @Test
    public void showByIdTest() {
        doReturn(cardTransfer).when(cardTransferMapper).toEntity(cardTransferDTO);
        doReturn(cardTransferDTO).when(cardTransferMapper).toDto(cardTransfer);
        cardTransferService.add(cardTransferDTO);

        when(cardTransferRepository.findById(cardTransfer.getId())).thenReturn(java.util.Optional.of(cardTransfer));

        CardTransferDTO expectedPhoneTransfer = new CardTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);

        assertEquals(expectedPhoneTransfer, cardTransferService.showById(cardTransferDTO.getId()));

        verify(cardTransferRepository).findById(cardTransfer.getId());

    }

}
