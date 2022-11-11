package com.masai.dao;

import com.masai.exception.FacultyException;
import com.masai.utilities.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FacultyDaoImpl implements FacultyDao{
    @Override
    public boolean facultyLogin(String username, String password) throws FacultyException {
        boolean result = false;
        boolean flag = false;

        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM FACULTY WHERE Username = ? AND Password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String u = rs.getString("username");
                String p = rs.getString("password");

                if(username.equals(u) && password.equals(p)){
                    flag = true;
                }
                if(flag) break;
            }

            if(flag) {
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| Faculty Login Successfully                                               |");
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("+-========================================================================-+");
                System.out.println("|                  WELCOME TO THE COURSE MONITORING SYSTEM                 |");
                result = true;

            }else {
                System.out.println("Invalid Credential....!");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new FacultyException(e.getMessage());
        }


        return result;
    }
}
