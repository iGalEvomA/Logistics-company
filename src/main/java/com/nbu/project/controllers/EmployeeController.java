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
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{email}")
    public Employee getByEmail(@PathVariable String email) {
        return employeeService.getByEmail(email);
    }
    @PutMapping("/{email}")
    public void updateEmployee(@PathVariable String email, @RequestBody Employee employee) {
        employeeService.updateEmployee(email, employee);
    }
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
    @DeleteMapping("/{email}")
    public void deleteEmployee(@PathVariable String email) {
        employeeService.deleteEmployee(email);
    }

}

