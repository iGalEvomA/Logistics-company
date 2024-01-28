package com.nbu.project.rowmapper;

import com.nbu.project.entities.Address;
import com.nbu.project.entities.Customer;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {
    public Address mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Address(
                resultSet.getInt("id"),
                resultSet.getString("address_type"),
                resultSet.getString("address")
        );
    }
}
