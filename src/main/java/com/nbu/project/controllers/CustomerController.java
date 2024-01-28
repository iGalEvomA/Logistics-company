package com.nbu.project.controllers;

import com.nbu.project.entities.Customer;
import com.nbu.project.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{email}")
    public void updateCustomer(@PathVariable String email, @RequestBody Customer customer) {
        customerService.update(customer);
    }

    @DeleteMapping("/{email}")
    public void deleteCustomer(@PathVariable String email) {
        customerService.deleteByEmail(email);
    }

}
