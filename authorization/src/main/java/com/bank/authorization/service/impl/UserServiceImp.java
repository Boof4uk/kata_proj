package com.bank.authorization.service.impl;

import com.bank.authorization.repository.UserRepository;
import com.bank.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

}
