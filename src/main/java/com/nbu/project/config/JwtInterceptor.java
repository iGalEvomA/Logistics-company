package com.nbu.project.config;

import com.nbu.project.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String authHeader = request.getHeader("Authentication");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Optional<Jws<Claims>> claimsJws = tokenService.parseJWT(token);
            if (claimsJws.isEmpty() || tokenService.isTokenInvalidated(claimsJws.get().getBody())) {
                response.setStatus(401);
                return false;
            }
            request.setAttribute("claims", claimsJws.get().getBody());
        }
        return true;
    }
}