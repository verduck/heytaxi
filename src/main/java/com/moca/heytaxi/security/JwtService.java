package com.moca.heytaxi.security;

import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Function;

public class JwtService {
    private JwtProperties jwtProperties;
    private final Key key;

    @Autowired
    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getPhone());
        LocalDate now = LocalDate.now();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.valueOf(now))
                .setExpiration(new Date(now.toEpochDay() * jwtProperties.getExpiration()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getPhone(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isExpired(String token) {
        final LocalDate expiration = getExpiration(token);
        return expiration.isBefore(LocalDate.now());
    }

    public boolean validateToken(String token, User user) {
        final String phone = getPhone(token);
        return (phone.equals(user.getPhone()) && !isExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private LocalDate getExpiration(String token) {
        return LocalDate.ofInstant(getClaim(token, Claims::getExpiration).toInstant(), ZoneId.systemDefault());
    }
}
