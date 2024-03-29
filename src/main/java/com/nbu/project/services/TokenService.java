package com.nbu.project.services;

import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

import io.jsonwebtoken.Claims;

@Service
@Setter
public class TokenService {

    @Value("classpath:private_key.der")
    private Resource private_key_bytes_resource;

    private final Set<String> invalidatedTokens = new TreeSet<>();

    public Optional<Jws<Claims>> parseJWT(String jwt) {
        try {
            byte[] privateKeyBytes = this.private_key_bytes_resource.getContentAsByteArray();
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(spec);

            return Optional.of(Jwts.parserBuilder().setSigningKey(privateKey).build().parseClaimsJws(jwt));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String generateJWT(Claims claims) {
        try {
            String privateKeyString = Arrays.toString(this.private_key_bytes_resource.getInputStream().readAllBytes());
            byte[] decodedKey = Base64.getDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(spec);

            return Jwts.builder()
                    .setClaims(claims)
                    .setId(String.valueOf(UUID.randomUUID()))
                    .signWith(privateKey)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT", e);
        }
    }

    public void invalidateToken(Claims claims) {
        invalidatedTokens.add(claims.getId());
    }

    public boolean isTokenInvalidated(Claims claims) {
        return invalidatedTokens.contains(claims.getId());
    }
}
