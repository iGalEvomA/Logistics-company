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
        // Check if the user is not authenticated
        if (principal == null) {
            // Redirect to the login page if not authenticated
            httpServletResponse.setHeader("Location", "/login");
            httpServletResponse.setStatus(302);
            return "Login first";
        }
        // Extract relevant customer information from OAuth2User
        String email = principal.getAttribute("email");

        // Create a Customer object
        Customer customer = new Customer(email);

        // Save the customer using the CustomerRepository if the email doesn't already exist
        if(!customerRepository.emailExists(email)){
            customerRepository.save(customer);
        }

        return "Hello " + principal.getAttribute("name") + "! Customer saved.";
    }
}
