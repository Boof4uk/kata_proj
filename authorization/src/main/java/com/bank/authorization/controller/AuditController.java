package com.bank.authorization.controller;


import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.service.AuditService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth-audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping
    @ApiOperation(value = "Get All")
    public ResponseEntity<List<AuditDto>> showAll() {
        List<AuditDto> auditDtoList = auditService.getAll();
        return new ResponseEntity<>(auditDtoList, HttpStatus.OK);
    }

}
