package com.nbu.project.dao;

import com.nbu.project.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    Employee save(Employee employee);

    public Optional<Employee> getByEmail(String email);
    public List<Employee> getAllEmployees();

    void update(Employee employee);

    void deleteByEmail(String email);

    List<Employee> getAll();
}
