package com.nbu.project.services;

import com.nbu.project.entities.Customer;
import com.nbu.project.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public void update(Customer customer){
        customerRepository.update(customer);
    }
    public void deleteByEmail(String email){
        customerRepository.deleteByEmail(email);
    }
}
