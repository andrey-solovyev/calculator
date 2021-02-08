package com.surf_test.calculator.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import java.security.Key;

@Component
public class JwtSupplier {
    private final Key key;
    private final JwtParser jwtParser;
}
