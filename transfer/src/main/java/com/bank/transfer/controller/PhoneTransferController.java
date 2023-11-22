package com.bank.transfer.controller;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.service.PhoneTransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * контроллер для работы с телефонными переводами
 */

@RestController
@RequestMapping("/api/phone-transfer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PhoneTransferController {

    /**
     * сервис для работы с телефонными переводами
     */

    private final PhoneTransferService phoneTransferService;

    /**
     * Получить все телефонные переводы
     *
     * @return объект ResponseEntity с коллекцией PhoneTransferDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "Получить все")
    public ResponseEntity<List<PhoneTransferDTO>> all() {
        List<PhoneTransferDTO> list = phoneTransferService.all();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Получить телефонный перевод по идентификатору
     *
     * @param id идентификатор перевода
     * @return объект ResponseEntity с объектом PhoneTransferDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить перевод по идентификатору")
    public ResponseEntity<PhoneTransferDTO> showById(@Valid @PathVariable Long id) {
        PhoneTransferDTO phoneTransferDTO = phoneTransferService.showById(id);
        return new ResponseEntity<>(phoneTransferDTO, HttpStatus.OK);
    }

    /**
     * Добавить телефонный перевод
     *
     * @param phoneTransferDTO объект, содержащий данные для перевода
     * @return объект ResponseEntity с объектом PhoneTransferDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Добавить перевод")
    public ResponseEntity<PhoneTransferDTO> newTransfer(@Valid @RequestBody(required = false) PhoneTransferDTO phoneTransferDTO) {
        phoneTransferService.add(phoneTransferDTO);
        return new ResponseEntity<>(phoneTransferDTO, HttpStatus.OK);
    }

    /**
     * Обновить данные для перевода
     *
     * @param phoneTransferDTO объект, содержащий данные для перевода
     * @return объект ResponseEntity с объектом PhoneTransferDTO и статусом ответа
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить данные")
    public ResponseEntity<PhoneTransferDTO> update(@Valid @RequestBody(required = false) PhoneTransferDTO phoneTransferDTO) {
        PhoneTransferDTO dto = phoneTransferService.update(phoneTransferDTO);
        if (dto != null) {
            return new ResponseEntity<>(phoneTransferDTO, HttpStatus.OK);
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
    public ResponseEntity<String> delete(@Valid @RequestBody(required = false) Long id, BindingResult bindingResult) {
        if (bindingResult == null || bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input data");
        }
        phoneTransferService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
