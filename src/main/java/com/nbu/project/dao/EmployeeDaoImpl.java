package com.nbu.project.dao;

import com.nbu.project.entities.Customer;
import com.nbu.project.entities.Employee;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
@Repository

public class EmployeeDaoImpl implements EmployeeDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Employee save(Employee employee) {
        String query = "INSERT INTO Employee (email, name, type, office_address_id) VALUES (?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, employee.email());
            ps.setString(2, employee.name());
            ps.setString(3, employee.type());
            ps.setInt(4, employee.officeAddressId());
            System.out.println("Executing query: " + query);
            System.out.println("Parameters: " + employee.email() + ", " + employee.name() + ", " + employee.type() + ", " + employee.officeAddressId());

            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Employee saved with username=" + employee.email());
            } else {
                System.out.println("Employee save failed with username=" + employee.email());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Optional<Employee> getByEmail(String email) {
        String query = "SELECT * FROM Employee WHERE email = ?";
        Employee emp = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee(
                            email,
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getInt("office_address_id")
                    );

                    System.out.println("Employee Found::" + emp);
                } else {
                    System.out.println("No Employee found with username=" + email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(emp);
    }

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select email from Employee";
        List<Employee> empList = new ArrayList<Employee>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Employee emp = new Employee(
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("office_address_id"));
                empList.add(emp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
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

    @Override
    public void update(Employee employee) {
        String query = "UPDATE Employee SET name=?, type=?, office_address_id=? WHERE email=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, employee.name());
            ps.setString(2, employee.type());
            ps.setInt(3, employee.officeAddressId());

            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Employee updated with email=" + employee.email());
            } else {
                System.out.println("No Employee found with email=" + employee.email());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByEmail(String email) {
        String query = "DELETE FROM Employee WHERE email=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Employee deleted with email=" + email);
            } else {
                System.out.println("No Employee found with email=" + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAll() {
        String query = "SELECT * FROM Employee";
        List<Employee> empList = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("office_address_id")
                );
                empList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empList;
    }
}

