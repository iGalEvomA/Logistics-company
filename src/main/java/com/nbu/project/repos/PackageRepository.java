package com.nbu.project.repos;

import com.nbu.project.entities.Package;
import com.nbu.project.rowmapper.PackageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PackageRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PackageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Package> findAll() {
        String sql = "SELECT * FROM package";
        return jdbcTemplate.query(sql, new PackageRowMapper());
    }

    public Package findById(int id) {
        String sql = "SELECT * FROM package WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PackageRowMapper(), id);
    }

    public void update(Package pack) {
        String sql = "UPDATE package SET status=?, weight=?, price=?, date_of_payment=?, " +
                "time_of_payment=?, sender_email=?, receiver_email=?, delivery_address_id=?, " +
                "register_email=?, courier_email=? WHERE id=?";
        jdbcTemplate.update(sql,
                pack.status(), pack.weight(), pack.price(), pack.dateOfPayment(),
                pack.timeOfPayment(), pack.senderEmail(), pack.receiverEmail(),
                pack.deliveryAddressId(), pack.registerEmail(), pack.courierEmail(),
                pack.id());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM package WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Package save(Package pkg) {
        String sql = "INSERT INTO package (status, weight, price, date_of_payment, time_of_payment, sender_email, receiver_email, " +
                "delivery_address_id, register_email, courier_email) " +
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
        return pkg;
    }


    public List<Package> findAllByEmployee(String employeeEmail) {
        String sql = "SELECT * FROM package JOIN package.register_email e WHERE e.id = :id";
        return jdbcTemplate.query(sql, new PackageRowMapper(), employeeEmail);
    }

    public List<Package> findAllByStatus(String status) {
        String sql = "SELECT * FROM package WHERE package.status = :status";
        return jdbcTemplate.query(sql, new PackageRowMapper(), status);
    }

    public List<Package> findAllBySender(String senderEmail) {
        String sql = "SELECT * FROM package WHERE package.sender_email = :sender_email";
        return jdbcTemplate.query(sql, new PackageRowMapper(), senderEmail);
    }

    public List<Package> findAllByReceiver(String receiverEmail) {
        String sql = "SELECT * FROM package WHERE package.receiver_email = :receiver_email";
        return jdbcTemplate.query(sql, new PackageRowMapper(), receiverEmail);
    }
}
