package com.nbu.project.dao;

import com.nbu.project.entities.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Customer customer) {
        String query = "insert into Customer (email) values (?)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, customer.email());
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Customer saved with email=" + customer.email());
            } else System.out.println("Customer save failed with email=" + customer.email());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Customer getByEmail(String email) {
        String query = "SELECT * FROM Customer WHERE email = ?";
        Customer emp = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Customer(
                            email
                    );

                    System.out.println("Customer Found::" + emp);
                } else {
                    System.out.println("No Customer found with username=" + email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override
    public void update(Customer customer) {
        String query = "update Customer where email=?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, customer.email());
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Customer updated with username=" + customer.email());
            } else System.out.println("No Customer found with username=" + customer.email());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteByEmail(String email) {
        String query = "delete from Customer where email=?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Customer deleted with email=" + email);
            } else System.out.println("No Customer found with email=" + email);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Customer> getAll() {
        String query = "select email from Customer";
        List<Customer> empList = new ArrayList<Customer>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer emp = new Customer(rs.getString("email"));
                empList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empList;
    }

}