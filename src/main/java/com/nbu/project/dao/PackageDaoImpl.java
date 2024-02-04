package com.nbu.project.dao;

import com.nbu.project.entities.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PackageDaoImpl implements PackageDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PackageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Package pkg) {
        String sql = "INSERT INTO package (status, weight, price, date_of_payment, time_of_payment, " +
                "sender_email, receiver_email, delivery_address_id, register_email, courier_email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                pkg.status(),
                pkg.weight(),
                pkg.price(),
                pkg.dateOfPayment(),
                pkg.timeOfPayment(),
                pkg.senderEmail(),
                pkg.receiverEmail(),
                pkg.deliveryAddressId(),
                pkg.registerEmail(),
                pkg.courierEmail()
        );
    }

    @Override
    public Package getById(int id) {
        String sql = "SELECT * FROM package WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PackageRowMapper(), id);
    }

    @Override
    public List<Package> getAll() {
        String sql = "SELECT * FROM package";
        return jdbcTemplate.query(sql, new PackageRowMapper());
    }

    @Override
    public void update(Package pkg) {
        String sql = "UPDATE package SET status = ?, weight = ?, price = ?, date_of_payment = ?, time_of_payment = ?, " +
                "sender_email = ?, receiver_email = ?, delivery_address_id = ?, register_email = ?, courier_email = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql,
                pkg.status(),
                pkg.weight(),
                pkg.price(),
                pkg.dateOfPayment(),
                pkg.timeOfPayment(),
                pkg.senderEmail(),
                pkg.receiverEmail(),
                pkg.deliveryAddressId(),
                pkg.registerEmail(),
                pkg.courierEmail(),
                pkg.id()
        );
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM package WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // PackageRowMapper implementation
    private static class PackageRowMapper implements org.springframework.jdbc.core.RowMapper<Package> {
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
}
