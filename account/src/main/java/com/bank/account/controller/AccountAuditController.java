package com.bank.account.controller;

import com.bank.account.dto.AccountAuditDTO;
import com.bank.account.service.AccountAuditService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account-audit")
public class AccountAuditController {


    private final AccountAuditService accountAuditService;

    @PostMapping
    @Operation(summary = "Create new Audit")
    public ResponseEntity<AccountAuditDTO> createAccountAudit(@Valid @RequestBody AccountAuditDTO accountAuditDTO) {
        accountAuditService.create(accountAuditDTO);
        return new ResponseEntity<>(accountAuditDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show Audit by id")
    public ResponseEntity<AccountAuditDTO> showByIdAccountAudit (@Valid @PathVariable Long id) {
        return new ResponseEntity<>(accountAuditService.findAccountAuditToId(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Show all audits")
    public ResponseEntity<List<AccountAuditDTO>> getAllAccountAudit () {
        return new ResponseEntity<>(accountAuditService.getAccountAuditList(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete audit")
    public ResponseEntity<AccountAuditDTO> deleteAccountAudit (@Valid @PathVariable Long id) {
        accountAuditService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update audit")
    public ResponseEntity<AccountAuditDTO> updateAccountAudit (@Valid @RequestBody AccountAuditDTO accountAuditDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(accountAuditDTO, HttpStatus.BAD_REQUEST);
        }
        accountAuditService.update(accountAuditDTO);
        return new ResponseEntity<>(accountAuditDTO,HttpStatus.OK);
    }
}
