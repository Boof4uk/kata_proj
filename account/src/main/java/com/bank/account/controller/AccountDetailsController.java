package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.service.AccountDetailsService;
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
@RequestMapping("/api/account-data")
public class AccountDetailsController {

    private final AccountDetailsService accountDetailsService;


    @PostMapping
    @Operation(summary = "Create new account details")
    public ResponseEntity<AccountDetailsDTO> createAccountDetails(@Valid @RequestBody AccountDetailsDTO accountDetailsDTO) {
        accountDetailsService.create(accountDetailsDTO);
        return new ResponseEntity<>(accountDetailsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show account details by id")
    public ResponseEntity<AccountDetailsDTO> showByIdAccountDetails (@Valid @PathVariable Long id) {
        return new ResponseEntity<>(accountDetailsService.findAccountDetailsById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Show all account details")
    public ResponseEntity<List<AccountDetailsDTO>> getAllAccountDetails () {
        return new ResponseEntity<>(accountDetailsService.getAccountDetailsList(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account details")
    public ResponseEntity<AccountDetailsDTO> deleteAccountDetails (@Valid @PathVariable Long id) {
        accountDetailsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account details")
    public ResponseEntity<AccountDetailsDTO> updateAccountDetails (@Valid @RequestBody AccountDetailsDTO accountDetailsDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(accountDetailsDTO, HttpStatus.BAD_REQUEST);
        }
         accountDetailsService.update(accountDetailsDTO);
        return new ResponseEntity<>(accountDetailsDTO,HttpStatus.OK);
    }

}
