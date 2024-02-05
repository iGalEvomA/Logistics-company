package com.nbu.project.repos;

import com.nbu.project.entities.Customer;
import com.nbu.project.rowmapper.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

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
    public void update(Customer customer) {
        String query = "UPDATE customer SET email WHERE email = ?";
        jdbcTemplate.update(query, customer.email());
    }
    public void deleteByEmail(String email) {
        String query = "DELETE FROM customer WHERE email = ?";
        jdbcTemplate.update(query, email);
    }
}
