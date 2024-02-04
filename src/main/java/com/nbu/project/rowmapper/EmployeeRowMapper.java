package com.nbu.project.rowmapper;

import com.nbu.project.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Employee(
                resultSet.getString("email"),
                resultSet.getString("name"),
                resultSet.getString("type"),
                resultSet.getInt("office_address_id")
        );
    }
}
