package com.surf_test.calculator.service;

import com.surf_test.calculator.data.dto.securityDto.LoginUserDto;
import com.surf_test.calculator.data.dto.securityDto.RegisterUserDto;
import com.surf_test.calculator.data.models.User;
import com.surf_test.calculator.data.models.UserRole;
import com.surf_test.calculator.data.repository.RoleRepository;
import com.surf_test.calculator.data.repository.UserRepository;
import com.surf_test.calculator.exception.NullPointerException;
import com.surf_test.calculator.data.dto.securityDto.AuthInfoDto;
import com.surf_test.calculator.security.JwtSupplier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtSupplier jwtSupplier;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,JwtSupplier jwtSupplier) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtSupplier = jwtSupplier;
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public User findByLoginUserDto(LoginUserDto loginUserDto){
          return findByNameAndPassword(loginUserDto.getName(),loginUserDto.getPassword());
    }
    public User findByNameAndPassword(String name, String password) {
        User user = findByName(name);
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
    public void registerNewUser(RegisterUserDto userDto){
        UserRole userRole= roleRepository.findByName("USER");
        User user=new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserRoles(Collections.singletonList(userRole));
        userRepository.save(user);
    }
    public AuthInfoDto generayeTokenFromUser(User user){
        return new AuthInfoDto(jwtSupplier.createTokenForUser(user.getName(),user.getSurname(),user.getUserRoles()));
    }

}
