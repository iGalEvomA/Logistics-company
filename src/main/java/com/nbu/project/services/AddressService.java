package com.nbu.project.services;

import com.nbu.project.entities.Address;
import com.nbu.project.entities.Customer;
import com.nbu.project.repos.AddressRepository;
import com.nbu.project.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        // Implement logic to retrieve all customers from the database
        return addressRepository.findAll();
    }

    public Address createAddress(Address address) {
        // Implement logic to save the customer to the database
        return addressRepository.save(address);
    }
    public void deleteAddressById(int id) {
        addressRepository.deleteById(id);
    }
}
