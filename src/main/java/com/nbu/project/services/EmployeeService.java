package com.nbu.project.services;
import com.nbu.project.dao.EmployeeDao;
import com.nbu.project.entities.Employee;
import com.nbu.project.repos.CustomerRepository;
import com.nbu.project.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee createEmployee(Employee employee) {
        // Add any business logic or validation before saving
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAll();
    }

    public Employee getByEmail(String email) {
        Optional<Employee> optionalEmployee = employeeRepository.getByEmail(email);
        return optionalEmployee.orElse(null);
    }

    public Employee updateEmployee(String email, Employee employee) {
        return employee;
    }

    public void deleteEmployee(String email) {
    }

    // Add other service methods (update, delete, etc.) as needed

}

