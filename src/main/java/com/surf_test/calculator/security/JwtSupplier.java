package com.surf_test.calculator.security;

import com.surf_test.calculator.data.models.User;
import com.surf_test.calculator.data.models.UserRole;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtSupplier {
    private final Key key;
    private final JwtParser jwtParser;

    public JwtSupplier(@Value("${jwtSecret}") String key) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build();
    }

    public String createTokenForUser(String name, String surname, List<UserRole> userRoles) {
        String roles = userRoles.stream()
                .map(UserRole::getName)
                .collect(Collectors.joining(","));

        Date exDate = Date.from(
                LocalDate.now()
                        .plusDays(7)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        return Jwts.builder()
                .setExpiration(exDate)
                .setSubject(name)
                .claim("roles", roles)
                .claim("surname", surname)
                .signWith(key)
                .compact();
    }
}

