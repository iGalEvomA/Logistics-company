package com.nbu.project.dao;

import com.nbu.project.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
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
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, customer.email());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Customer saved with email="+customer.email());
            }else System.out.println("Customer save failed with email="+customer.email());
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
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
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

//    @Override
//    public Customer getByEmail(String username) {
//        String query = "select token1, token2 from Customer where email = ?";
//        Customer emp = null;
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try{
//            con = dataSource.getConnection();
//            ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            if(rs.next()){
//                emp = new Customer();
//                emp.setEmail(username);
//                emp.setToken1(rs.getString("token1"));
//                emp.setToken2(rs.getString("token2"));
//                System.out.println("Customer Found::"+emp);
//            }else{
//                System.out.println("No Customer found with email="+username);
//            }
//        }catch(SQLException e){
//            e.printStackTrace();
//        }finally{
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return emp;
//    }

//    @Override
//    public void update(Customer customer) {
//        String query = "update Customer set token1=?, token2=? where email=?";
//        Connection con = null;
//        PreparedStatement ps = null;
//        try{
//            con = dataSource.getConnection();
//            ps = con.prepareStatement(query);
//            ps.setString(1, customer.getToken1());
//            ps.setString(2, customer.getToken2());
//            ps.setString(3, customer.getEmail());
//            int out = ps.executeUpdate();
//            if(out !=0){
//                System.out.println("Customer updated with username="+customer.getEmail());
//            }else System.out.println("No Customer found with username="+customer.getEmail());
//        }catch(SQLException e){
//            e.printStackTrace();
//        }finally{
//            try {
//                ps.close();
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void deleteByEmail(String email) {
        String query = "delete from Customer where email=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Customer deleted with email="+email);
            }else System.out.println("No Customer found with email="+email);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
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
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Customer emp = new Customer(rs.getString("email"));
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

}