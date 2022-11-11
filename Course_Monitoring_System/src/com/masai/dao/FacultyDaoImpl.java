package com.masai.dao;

import com.masai.exception.CoursePlanException;
import com.masai.exception.FacultyException;
import com.masai.model.CoursePlan;
import com.masai.utilities.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<CoursePlan> viewCoursePlan() throws CoursePlanException {
        List<CoursePlan> coursePlans = new ArrayList<>();
        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM CoursePlan");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int p = rs.getInt("PlanID");
                int b = rs.getInt("BatchID");
                int d = rs.getInt("DayNumber");
                String t = rs.getString("Topic");
                String s = rs.getString("Status");
                CoursePlan cp = new CoursePlan(p,b,d,t,s);
                coursePlans.add(cp);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new CoursePlanException(e.getMessage());
        }

        if(coursePlans.size() == 0){
            throw new CoursePlanException("No any record found");
        }
        return coursePlans;
    }
}
