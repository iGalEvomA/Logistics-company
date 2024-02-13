package com.nbu.project.controllers;

import com.nbu.project.dto.EmployeeRegisterRequest;
import com.nbu.project.dto.LoginRequest;
import com.nbu.project.entities.Customer;
import com.nbu.project.entities.Employee;
import com.nbu.project.repos.CustomerRepository;
import com.nbu.project.repos.EmployeeRepository;
import com.nbu.project.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
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

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    @PostMapping("/customer/register")
    public ResponseEntity<String> register(@RequestBody @NotNull String email) {
        if (customerRepository.emailExists(email) || employeeRepository.emailExists(email)) {
            return ResponseEntity.status(409).build();
        }
        customerRepository.save(new Customer(email));
        Claims claims = Jwts.claims().setSubject(email).setIssuedAt(Date.from(Instant.now()));
        String jwt = tokenService.generateJWT(claims);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/employee/register")
    public ResponseEntity<String> register(@RequestBody EmployeeRegisterRequest registerRequest) {
        if (customerRepository.emailExists(registerRequest.getEmail()) || employeeRepository.emailExists(registerRequest.getEmail())) {
            return ResponseEntity.status(409).build();
        }
        Employee employee = new Employee(registerRequest.getEmail(), registerRequest.getName(), registerRequest.getType(), registerRequest.getOfficeId());
        employeeRepository.save(employee);
        Claims claims = Jwts.claims().setSubject(registerRequest.getEmail()).setIssuedAt(Date.from(Instant.now()));
        String jwt = tokenService.generateJWT(claims);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (!customerRepository.emailExists(loginRequest.getEmail()) && !employeeRepository.emailExists(loginRequest.getEmail())) {
            return ResponseEntity.status(401).build();
        }
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