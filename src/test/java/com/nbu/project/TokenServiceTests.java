package com.nbu.project;

import com.nbu.project.services.TokenService;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TokenServiceTests {

    private TokenService tokenService;

    @BeforeEach
    public void setup() {
        Resource private_key_bytes_resource = new ClassPathResource("private_key.der");
        tokenService = new TokenService();
        tokenService.setPrivate_key_bytes_resource(private_key_bytes_resource);
    }

    @Test
    public void testParseJWT() throws Exception {
        final Resource private_key_bytes_resource = new ClassPathResource("private_key.der");
        byte[] privateKeyBytes = private_key_bytes_resource.getContentAsByteArray();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec);

        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(3600); // expires in 1 hour
        Date expDate = Date.from(expiration);
        String token = Jwts.builder()
                .setSubject("test")
                .setExpiration(expDate)
                .setIssuedAt(Date.from(now))
                .signWith(privateKey)
                .compact();

        var parsed = tokenService.parseJWT(token);
        assertFalse(parsed.isEmpty());
        assertEquals("test", parsed.get().getBody().getSubject());
    }
}