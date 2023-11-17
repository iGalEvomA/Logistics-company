package com.nbu.project.services;

import com.nbu.project.entities.Customer;
import com.nbu.project.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        // Implement logic to retrieve all customers from the database
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        // Implement logic to save the customer to the database
        return customerRepository.save(customer);
    }
}
