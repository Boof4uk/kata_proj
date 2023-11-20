package com.bank.authorization.util;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Init {

    private final UserService userService;

//    @PostConstruct
//    public void initializedUserDataBase() {
//        userService.add(new UserDto(1L, "ADMIN", 1L, "admin"));
//        userService.add(new UserDto(2L, "USER", 2L, "user"));
//    }


}
