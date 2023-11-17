package com.nbu.project.repos;

import com.nbu.project.entities.Customer;
import com.nbu.project.rowmapper.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    public Customer save(Customer customer) {
        String sql = "INSERT INTO customer (email) VALUES (?)";
        jdbcTemplate.update(sql,customer.email());
        return customer;
    }
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM customer WHERE email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }
}
