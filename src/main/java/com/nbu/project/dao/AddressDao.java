package com.nbu.project.dao;

import com.nbu.project.entities.Address;
import com.nbu.project.entities.Customer;

import java.util.List;

public interface AddressDao {

    //Create
    public void save(Address address);
    //Update
    public void update(Address address);
    //Delete
    public void deleteById(int id);
    //Get All
    public List<Address> getAll();
}
