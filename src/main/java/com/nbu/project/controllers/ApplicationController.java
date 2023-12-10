package com.nbu.project.controllers;

import com.nbu.project.entities.Customer;
import com.nbu.project.repos.CustomerRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private CustomerRepository customerRepository;
    @Autowired
    public ApplicationController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping("/")
    public String home(@AuthenticationPrincipal @NotNull OAuth2User principal, HttpServletResponse httpServletResponse) {
        if (principal == null) {
            httpServletResponse.setHeader("Location", "/login");
            httpServletResponse.setStatus(302);
            return "Login first";
        }
        String email = principal.getAttribute("email");

        Customer customer = new Customer(email);

        if(!customerRepository.emailExists(email)){
            customerRepository.save(customer);
        }

        return "Hello " + principal.getAttribute("name") + "! Customer saved.";
    }
}
