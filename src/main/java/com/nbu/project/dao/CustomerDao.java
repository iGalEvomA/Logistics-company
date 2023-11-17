package com.nbu.project.dao;

import com.nbu.project.entities.Customer;

import java.util.List;

public interface CustomerDao {

    //Create
    public void save(Customer customer);
    //Read
    public Customer getByEmail(String email);
    //Update
    public void update(Customer customer);
    //Delete
    public void deleteByEmail(String email);
    //Get All
    public List<Customer> getAll();
}
