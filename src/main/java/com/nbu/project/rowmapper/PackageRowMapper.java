package com.nbu.project.rowmapper;

import com.nbu.project.entities.Package;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageRowMapper implements RowMapper<Package> {

    @Override
    public Package mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Package(
                resultSet.getInt("id"),
                resultSet.getString("status"),
                resultSet.getDouble("weight"),
                resultSet.getDouble("price"),
                resultSet.getDate("date_of_payment"),
                resultSet.getTimestamp("time_of_payment"),
                resultSet.getString("sender_email"),
                resultSet.getString("receiver_email"),
                resultSet.getInt("delivery_address_id"),
                resultSet.getString("register_email"),
                resultSet.getString("courier_email")
        );
    }
}
