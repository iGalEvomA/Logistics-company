package com.nbu.project.dao;

import com.nbu.project.entities.Customer;

import java.util.List;

public interface CustomerDao {

    //Create
    void save(Customer customer);

    //Read
    Customer getByEmail(String email);

    //Update
    void update(Customer customer);

    //Delete
    void deleteByEmail(String email);

    //Get All
    List<Customer> getAll();
}
