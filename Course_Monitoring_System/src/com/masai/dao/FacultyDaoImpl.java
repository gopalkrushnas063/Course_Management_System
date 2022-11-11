package com.masai.dao;

import com.masai.exception.CoursePlanException;
import com.masai.exception.FacultyException;
import com.masai.model.CoursePlan;
import com.masai.model.FacultyCourseView;
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
    public List<FacultyCourseView> viewCoursePlan(int fid) throws CoursePlanException {
        List<FacultyCourseView> coursePlans = new ArrayList<>();
        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT f.FacultyID,f.FacultyName,b.BatchID,b.BatchStartDate,c.Topic,c.Status FROM Faculty f INNER JOIN Batch b ON f.FacultyID = b.FacultyID INNER JOIN CoursePlan c ON b.BatchID = c.BatchID WHERE f.FacultyID = ? GROUP BY b.BatchID");
            ps.setInt(1,fid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                FacultyCourseView facultyCourseViews = new FacultyCourseView(rs.getInt("FacultyID"),rs.getString("FacultyName"),rs.getInt("BatchID"),rs.getString("BatchStartDate"),rs.getString("Topic"),rs.getString("Status"));

                 coursePlans.add(facultyCourseViews);

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

    @Override
    public String dayWisePlan(CoursePlan coursePlan) throws FacultyException {
        String message = "Unable to add ";


        try(Connection conn = DBUtility.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("INSERT INTO CoursePlan(BatchID , DayNumber , Topic , Status) VALUES (?,?,?,?)");
            ps.setInt(1,coursePlan.getBatchid());
            ps.setInt(2,coursePlan.getDaynumber());
            ps.setString(3,coursePlan.getTopic());
            ps.setString(4,coursePlan.getStatus());

            int x = ps.executeUpdate();
            if(x>0){
                message = x+" Day-wise planner added  successfully...!";
            }else {
                throw new FacultyException("Something went wrong");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new FacultyException(e.getMessage());
        }
        return message;
    }
}
