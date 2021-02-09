package com.surf_test.calculator.security;

import com.surf_test.calculator.data.models.User;
import com.surf_test.calculator.data.models.UserRole;
import com.surf_test.calculator.service.UserService;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtSupplier {
    private final Key key;
    private final JwtParser jwtParser;
    private static Logger logger = LoggerFactory.getLogger(JwtSupplier.class);


    public JwtSupplier(@Value("${jwtSecret}") String keys) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keys));
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build();
    }

    /**
     * генерируем токен
     */
    public String createTokenForUser(String name, String surname, List<UserRole> userRoles) {
        Date exDate = Date.from(
                LocalDate.now()
                        .plusDays(7)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        return Jwts.builder()
                .setExpiration(exDate)
                .setSubject(name)
                .claim("surname", surname)
                .signWith(key)
                .compact();
    }
}

