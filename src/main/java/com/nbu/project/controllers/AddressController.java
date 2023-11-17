package com.nbu.project.controllers;

import com.nbu.project.entities.Address;
import com.nbu.project.entities.Customer;
import com.nbu.project.services.AddressService;
import com.nbu.project.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        addressService.deleteAddressById(id);
    }
}
