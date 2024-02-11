package com.nbu.project.controllers;

import com.nbu.project.dto.LoginRequest;
import com.nbu.project.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Claims claims = Jwts.claims().setSubject(loginRequest.getEmail()).setIssuedAt(Date.from(Instant.now()));
        String jwt = tokenService.generateJWT(claims);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        final Claims claims = (Claims) request.getAttribute("claims");
        tokenService.invalidateToken(claims);
        request.logout();
        return "redirect:/";
    }
}