package com.nbu.project.dao;

import com.nbu.project.entities.Address;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Address address) {
        String query = "insert into Address (id, address_type, address) values (?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, address.id());
            ps.setString(2, address.address_type());
            ps.setString(3, address.address());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Address saved with id="+address.id());
            }else System.out.println("Address save failed with id="+address.id());
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
    public void update(Address address) {
        String query = "UPDATE Address SET id=?, address_type=?, address=? WHERE id=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, address.id());
            ps.setString(2, address.address_type());
            ps.setString(3, address.address());

            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Address updated with id=" + address.id());
            } else {
                System.out.println("No Address found with id=" + address.id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id){
        String query = "DELETE FROM Address WHERE id=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("Address deleted with id=" + id);
            } else {
                System.out.println("No Address found with id=" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Address> getAll() {
        String query = "select id, address_type, address from Address";
        List<Address> empList = new ArrayList<Address>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Address emp = new Address(
                        rs.getInt("id"),
                        rs.getString("address_type"),
                        rs.getString("address")
                );
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
