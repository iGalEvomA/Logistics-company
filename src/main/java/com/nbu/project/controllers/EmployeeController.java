package com.nbu.project.controllers;
import com.nbu.project.entities.Employee;
import com.nbu.project.services.EmployeeService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Mock endpoint for creating a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Mock endpoint for updating an existing employee
    @PutMapping("/{email}")
    public Employee updateEmployee(@PathVariable String email, @RequestBody Employee employee) {
        return employeeService.updateEmployee(email, employee);
    }

    // Mock endpoint for deleting an employee by username
    @DeleteMapping("/{email}")
    public void deleteEmployee(@PathVariable String email) {
        employeeService.deleteEmployee(email);
    }

    // Mock endpoint for retrieving all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}

