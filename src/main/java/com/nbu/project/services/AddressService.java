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
        return addressRepository.findAll();
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
    public void deleteAddressById(int id) {
        addressRepository.deleteById(id);
    }
    public void update(int id, Address address){
        addressRepository.update(id, address);
    }
}
