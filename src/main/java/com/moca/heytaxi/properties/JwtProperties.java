package com.moca.heytaxi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secret;
    private final long expiration;

    public JwtProperties(String secret, long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public long getExpiration() {
        return expiration;
    }
}
