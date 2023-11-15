package com.bank.authorization.controller;

import com.bank.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth-user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


}
