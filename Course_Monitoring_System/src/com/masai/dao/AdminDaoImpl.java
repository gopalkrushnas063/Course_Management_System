package com.masai.dao;

import com.masai.exception.AdminException;
import com.masai.model.Admin;
import com.masai.utilities.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao{
    @Override
    public boolean adminLogin(String username, String password) throws AdminException {
        boolean result = false;
        boolean flag = false ;
        try (Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String u = rs.getString("username");
                String p = rs.getString("password");

                if(username.equals(u)&&password.equals(p)) {
                    flag=true;
                }

                if(flag) break;
            }
            if(flag) {
                System.out.println("Admin Login Successfully.....!");
                result = true;

            }else {
                System.out.println("Incorrect Credential");
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new AdminException(e.getMessage());
        }


        return result;
    }
}
