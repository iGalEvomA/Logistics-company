package com.nbu.project.repos;

import com.nbu.project.entities.Employee;
import com.nbu.project.rowmapper.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee save(Employee employee) {
        String query = "INSERT INTO employee (email, name, type, office_address_id) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(query, employee.email(), employee.name(), employee.type(), employee.officeAddressId());
        return employee;
    }

    public List<Employee> getAll() {
        String query = "SELECT * FROM employee";
        return jdbcTemplate.query(query, new EmployeeRowMapper());
    }

    public Optional<Employee> getByEmail(String email) {
        String query = "SELECT * FROM employee WHERE email = ?";
        try {
            Employee employee = jdbcTemplate.queryForObject(query, new EmployeeRowMapper(), email);
            return Optional.ofNullable(employee);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void update(String email, Employee employee) {
        String query = "UPDATE employee SET name = ?, type = ?, office_address_id = ? WHERE email = ?";
        jdbcTemplate.update(query, employee.name(), employee.type(), employee.officeAddressId(), email);
    }

    public void deleteByEmail(String email) {
        String query = "DELETE FROM employee WHERE email = ?";
        jdbcTemplate.update(query, email);
    }
}

