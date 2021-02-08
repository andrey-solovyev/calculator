package com.surf_test.calculator.service;

import com.surf_test.calculator.data.models.User;
import com.surf_test.calculator.data.repository.UserRepository;
import com.surf_test.calculator.exception.NullPointerException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByLoginAndPassword(String login, String password) {
        var user = userRepository.findByName(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User findById(String id) {
        User user = userRepository.findById(id);
        if (user!=null) return user;
        else throw new NullPointerException();
    }

}
