package com.nbu.project.repos;

import com.nbu.project.entities.Address;
import com.nbu.project.rowmapper.AddressRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Address> findAll() {
        String sql = "SELECT * FROM address";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    public Address save(Address address) {
        String sql = "INSERT INTO address (id, address_type, address) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, address.id(), address.address_type(), address.address());
        return address;
    }

    public void update(int id, Address address) {
        String query = "UPDATE address SET address_type = ?, address = ? WHERE id = ?";
        jdbcTemplate.update(query, address.address_type(), address.address(), id);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM address WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
