package com.nbu.project.controllers;

import com.nbu.project.dto.LoginRequest;
import com.nbu.project.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.Date;

@RestController
public class LoginController {

    private final TokenService tokenService;

    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Claims claims = Jwts.claims().setSubject(loginRequest.getEmail()).setIssuedAt(Date.from(Instant.now()));
        String jwt = tokenService.generateJWT(claims);
        return ResponseEntity.ok(jwt);
    }
}