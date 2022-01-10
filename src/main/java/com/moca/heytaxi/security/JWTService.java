package com.moca.heytaxi.security;

import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.properties.JWTProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JWTService {
    private JWTProperties jwtProperties;

    @Autowired
    public JWTService(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getPhone());
        LocalDate now = LocalDate.now();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.valueOf(now))
                .setExpiration(new Date(now.getLong()))
                .compact();
    }
}
