package com.nbu.project.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal @NotNull OAuth2User principal, HttpServletResponse httpServletResponse) {
        if (principal == null) {
            httpServletResponse.setHeader("Location", "/login");
            httpServletResponse.setStatus(302);
            return "Login first";
        }
        return "Hello " + principal.getAttribute("name") + " !";
    }
}
