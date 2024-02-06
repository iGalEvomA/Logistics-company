package com.nbu.project.dao;

import com.nbu.project.entities.Address;

import java.util.List;

public interface AddressDao {

    //Create
    void save(Address address);

    //Update
    void update(Address address);

    //Delete
    void deleteById(int id);

    //Get All
    List<Address> getAll();
}
