package com.masai.dao;

import com.masai.exception.AdminException;
import com.masai.exception.CourseException;
import com.masai.model.Admin;
import com.masai.model.Course;
import com.masai.utilities.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
                System.out.println("Invalid Credential....!");
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new AdminException(e.getMessage());
        }


        return result;
    }

    @Override
    public String  addCourse(Course course) throws CourseException {
        String message = "Not added...";

        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO COURSE(Course_Name,Fee,Course_Description) VALUES(?,?,?)");

            ps.setString(1,course.getCourseName());
            ps.setInt(2,course.getFee());
            ps.setString(3,course.getCourseDescription());

            int x = ps.executeUpdate();

            if(x>0){
                message = x + "Course details added successfully";
            }else {
                throw new CourseException("Something went wrong , so unable to add course details . Kindly try again....");
            }
        }catch (SQLException e){
            e.printStackTrace();

        }

        return message;
    }

    @Override
    public String updateCourse(int cid) throws CourseException {
        String message = "Provide valid course details";
        Scanner sc = new Scanner(System.in);
        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT CourseID FROM COURSE WHERE CourseID = ?");
            ps.setInt(1,cid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                boolean check = true;
                while (check){
                    System.out.println("Please select your choice to update course details :" +'\n'+
                            "1. Course Name" +'\n'+
                            "2. Course Fee" +'\n'+
                            "3. Course Description");
                    int c = sc.nextInt();

                    switch (c){
                        case 1: {
                            System.out.println("Enter updated course name : ");
                            String cname = sc.next();

                            PreparedStatement ps1 = conn.prepareStatement("UPDATE COURSE SET Course_Name = ? WHERE CourseId = ?");
                            ps1.setString(1, cname);
                            ps1.setInt(2, cid);

                            int x1 = ps1.executeUpdate();
                            if (x1 > 0) {
                                message = x1 + " Course name updated successfully...!";
                            } else {
                                System.out.println("Unable to update course name ....!");
                            }
                            check = false;
                            break;
                        }
                        case 2 :{
                            System.out.println("Enter updated course fee : ");
                            int fee = sc.nextInt();

                            PreparedStatement ps2 = conn.prepareStatement("UPDATE Course SET fee = ? WHERE CourseID = ?");
                            ps2.setInt(1,fee);
                            ps2.setInt(2,cid);


                            int x2 = ps2.executeUpdate();
                            if (x2 > 0) {
                                message = x2 + " Course fee updated successfully...!";
                            } else {
                                System.out.println("Unable to update course fee ....!");
                            }
                            check = false;
                            break;
                        }
                        case 3 :{
                            System.out.println("Enter updated course description : ");
                            String  desc = sc.next();

                            PreparedStatement ps3 = conn.prepareStatement("UPDATE Course SET Course_Desctiption = ? WHERE CourseID = ?");
                            ps3.setString(1,desc);
                            ps3.setInt(2,cid);


                            int x3 = ps3.executeUpdate();
                            if (x3 > 0) {
                                message = x3 + " Course description updated successfully...!";
                            } else {
                                System.out.println("Unable to update course description ....!");
                            }
                            check = false;
                            break;
                        }
                        default:
                            System.out.println("Invalid choice , kindly choose from the given choice list...");
                            check = true;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return message;
    }
}
