package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.service.AccountTransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
 * контроллер для работы с переводами между счетами
 */

@RestController
@RequestMapping("/api/account-transfer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AccountTransferController {

    /**
     * сервис для работы с переводами между счетами
     */
    private final AccountTransferService accountTransferService;

    /**
     * Получить все переводы между счетами
     *
     * @return объект ResponseEntity с коллекцией AccountTransferDTO и статусом ответа
     */

    @GetMapping
    @ApiOperation(value = "Получить все")
    public ResponseEntity<List<AccountTransferDTO>> all() {
        List<AccountTransferDTO> list = accountTransferService.all();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Получить перевод по идетификатору
     *
     * @param id идентификатор перевода
     * @return объект ResponseEntity с объектом AccountTransferDTO и статусом ответа
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить перевод по идентификатору")
    public ResponseEntity<AccountTransferDTO> showById(@Valid @PathVariable Long id) {
        AccountTransferDTO accountTransferDTO = accountTransferService.showById(id);
        return new ResponseEntity<>(accountTransferDTO, HttpStatus.OK);
    }

    /**
     * Новый перевод между счетами
     *
     * @param accountTransferDTO объект с данными для перевода
     * @return объект ResponseEntity с объектом AccountTransferDTO и статусом ответа
     */

    @PostMapping
    @ApiOperation(value = "Добавить сущность")
    public ResponseEntity<AccountTransferDTO> newTransfer(@Valid @RequestBody(required = false) AccountTransferDTO accountTransferDTO) {
        accountTransferService.add(accountTransferDTO);
        return new ResponseEntity<>(accountTransferDTO, HttpStatus.OK);
    }

    /**
     * Обновить данные для перевода
     *
     * @param accountTransferDTO объект с данными для перевода
     * @return объект ResponseEntity с объектом AccountTransferDTO и статусом ответа
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить данные")
    public ResponseEntity<AccountTransferDTO> update(@Valid @RequestBody(required = false) AccountTransferDTO accountTransferDTO) {
        AccountTransferDTO dto = accountTransferService.update(accountTransferDTO);
        if (dto != null) {
            return new ResponseEntity<>(accountTransferDTO, HttpStatus.OK);
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
        accountTransferService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}

