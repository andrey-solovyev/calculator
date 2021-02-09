package com.surf_test.calculator.controller;

import com.surf_test.calculator.data.dto.securityDto.LoginUserDto;
import com.surf_test.calculator.data.dto.securityDto.RegisterUserDto;
import com.surf_test.calculator.data.models.User;
import com.surf_test.calculator.data.dto.securityDto.AuthInfoDto;
import com.surf_test.calculator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = POST, path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody RegisterUserDto registerUserDto) {
        userService.registerNewUser(registerUserDto);
    }

    @RequestMapping(method = POST, path = "/login")
    public AuthInfoDto loginUser(@RequestBody LoginUserDto loginUserDto) {
        logger.error("login");
        User user = userService.findByLoginUserDto(loginUserDto);
        if (user == null) {
            throw new RuntimeException("Invalid login or password");
        }
        return userService.generayeTokenFromUser(user);
    }


}
