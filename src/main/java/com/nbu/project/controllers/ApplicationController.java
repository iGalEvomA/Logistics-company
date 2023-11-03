package com.nbu.project.controllers;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal @NotNull OAuth2User principal) {
        if (principal == null) {
            // TODO: Redirect to login page
            return "Hello World !";
        }
        return "Hello " + principal.getAttribute("name") + " !";
    }
}
