package com.bank.history.controllers;

import com.bank.history.exceptions.HistoryNotCreatedException;
import com.bank.history.exceptions.HistoryNotDeletedException;
import com.bank.history.exceptions.HistoryNotFoundException;
import com.bank.history.dto.HistoryDTO;
import com.bank.history.exceptions.HistoryNotUpdatedException;
import com.bank.history.services.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HistoryController {


    private final HistoryService historyService;

    @Operation(summary = "Creating new history")
    @PostMapping()
    public ResponseEntity<HistoryDTO> createNewHistory(@Valid @RequestBody HistoryDTO historyDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder inputErrors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                inputErrors.append(fieldError.getDefaultMessage()).append("; \n");
            }
            throw new HistoryNotCreatedException("Incorrect history data: \n" + inputErrors);
        }
        return new ResponseEntity<>(historyService.save(historyDTO), HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Getting list of all histories")
    public ResponseEntity<List<HistoryDTO>> getAllHistories() {
        return new ResponseEntity<>(historyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting history by ID")
    public ResponseEntity<HistoryDTO> getHistoryById(@PathVariable Long id) {
        return new ResponseEntity<>(historyService.getById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updating history")
    public ResponseEntity<HistoryDTO> updateHistory(@Valid @RequestBody HistoryDTO historyDTO,
                                                    @PathVariable Long id,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder inputErrors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                inputErrors.append(error.getDefaultMessage()).append("; \n");
            }
            throw new HistoryNotUpdatedException("Incorrect history data \n" + inputErrors);
        }
        return new ResponseEntity<>(historyService.update(historyDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting history by ID")
    public ResponseEntity<HistoryDTO> deleteHistory(@PathVariable Long id) {
        historyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
