package com.bank.transfer.controller;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.service.CardTransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * контроллер для работы с переводами на карту
 */

@RestController
@RequestMapping("/api/card-transfer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CardTransferController {

    /**
     * сервис для работы с переводами на карту
     */
    private final CardTransferService cardTransferService;

    /**
     * Получить все переводы на карту
     *
     * @return объект ResponseEntity с коллекцией CardTransferDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "Получить все переводы")
    public ResponseEntity<List<CardTransferDTO>> all() {
        List<CardTransferDTO> list = cardTransferService.all();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Получить перевод по идентификатору
     *
     * @param id идентификатор перевода
     * @return объект ResponseEntity с объектом CardTransferDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить перевод по идентификатору")
    public ResponseEntity<CardTransferDTO> showById(@Valid @PathVariable Long id) {
        CardTransferDTO cardTransferDTO = cardTransferService.showById(id);
        return new ResponseEntity<>(cardTransferDTO, HttpStatus.OK);
    }

    /**
     * Добавить перевод
     *
     * @param cardTransferDTO объект, содержащий данные для перевода
     * @return объект ResponseEntity с объектом CardTransferDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Добавить перевод")
    public ResponseEntity<CardTransferDTO> newTransfer(@Valid @RequestBody(required = false) CardTransferDTO cardTransferDTO) {
        cardTransferService.add(cardTransferDTO);
        return new ResponseEntity<>(cardTransferDTO, HttpStatus.OK);
    }

    /**
     * Обновить данные для перевода
     *
     * @param cardTransferDTO объект, содержащий данные для перевода
     * @return объект ResponseEntity с объектом CardTransferDTO и статусом ответа
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить данные")
    public ResponseEntity<CardTransferDTO> update(@Valid @RequestBody(required = false) CardTransferDTO cardTransferDTO) {
        CardTransferDTO dto = cardTransferService.update(cardTransferDTO);
        if (dto != null) {
            return new ResponseEntity<>(cardTransferDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Удалить перевод по идентификатору
     *
     * @param id            идентификатор перевода
     * @param bindingResult обработка ошибок
     * @return объект ResponseEntity со строкой ответа
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить перевод")
    public ResponseEntity<String> delete(@Valid @RequestBody(required = false) Long id, @Nullable BindingResult bindingResult) {
        if (bindingResult == null || bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input data");
        }
        cardTransferService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
