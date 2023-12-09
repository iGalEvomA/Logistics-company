package com.nbu.project.service;

import com.nbu.project.entity.Employee;
import com.nbu.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(String email) {
        return employeeRepository.findById(email).orElse(null);
    }
}
