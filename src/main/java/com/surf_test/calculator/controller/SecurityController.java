package com.surf_test.calculator.controller;

import com.surf_test.calculator.data.dto.RegisterUserDto;
import com.surf_test.calculator.data.dto.UserDto;
import com.surf_test.calculator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/v1/security")
@Slf4j
public class SecurityController {
    private static Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public SecurityController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = POST, path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody RegisterUserDto registerUserDto) {
        registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        userService.registerNewUser(registerUserDto);
    }

//    @RequestMapping(method = POST, path = "/login")


}
