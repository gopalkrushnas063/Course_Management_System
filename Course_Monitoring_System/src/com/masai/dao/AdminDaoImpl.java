package com.masai.dao;

import com.masai.exception.AdminException;
import com.masai.exception.BatchException;
import com.masai.exception.CourseException;
import com.masai.exception.FacultyException;
import com.masai.model.Admin;
import com.masai.model.Batch;
import com.masai.model.Course;
import com.masai.model.Faculty;
import com.masai.utilities.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| Admin Login Successfully                                                 |");
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("+-========================================================================-+");
                System.out.println("|                  WELCOME TO THE COURSE MONITORING SYSTEM                 |");
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

    @Override
    public List<Course> viewCourse() throws CourseException {
        List<Course> courses = new ArrayList<>();
        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM COURSE");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Course course = new Course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("Course_Name"));
                course.setFee(rs.getInt("fee"));
                course.setCourseDescription(rs.getString("Course_Description"));

                courses.add(course);
            }
        }catch (SQLException e){
            throw new CourseException(e.getMessage());
        }

        if(courses.size() == 0){
            throw new CourseException("No any course found");
        }
        return courses;
    }

    @Override
    public String updateBatch(int batchid) throws BatchException {
        String message = "Provide valid batch details";
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT BATCHID FROM BATCH WHERE BATCHID=?");
            ps.setInt(1,batchid);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                while (flag){
                    System.out.println("Please Select As Per Your Requirement : " +"\n"+
                            "1. Update Course ID" +"\n"+
                            "2. Update Faculty ID" +"\n"+
                            "3. Update Number Of Students" +"\n"+
                            "4. Update Batch Start Date" +"\n"+
                            "5. Update Duration");
                    int choice = sc.nextInt();
                    switch (choice){
                        case 1:
                            System.out.println("Enter Course ID For Update :");
                            int cid = sc.nextInt();
                            PreparedStatement ps1 = conn.prepareStatement("UPDATE BATCH SET CourseID = ? WHERE BatchID = ?");
                            ps1.setInt(1,cid);
                            ps1.setInt(2,batchid);

                            int x = ps1.executeUpdate();
                            if(x>0){
                                message = "Batch ID Updated Successfully....";
                            }else{
                                throw new BatchException("Unable to update Batch ID");
                            }
                            flag = false;
                            break;
                        case 2:
                            System.out.println("Enter Faculty ID For Update :");
                            int fid = sc.nextInt();
                            PreparedStatement ps2 = conn.prepareStatement("UPDATE BATCH SET FacultyID = ? WHERE BatchID = ?");
                            ps2.setInt(1,fid);
                            ps2.setInt(2,batchid);

                            int x2 = ps2.executeUpdate();
                            if(x2>0){
                                message = "Faculty ID Updated Successfully....";
                            }else{
                                throw new BatchException("Unable to update Faculty ID");
                            }
                            flag = false;
                            break;
                        case 3:
                            System.out.println("Enter Student Number For Update :");
                            int snumber = sc.nextInt();
                            PreparedStatement ps3 = conn.prepareStatement("UPDATE BATCH SET NumberOfStudent = ? WHERE BatchID = ?");
                            ps3.setInt(1,snumber);
                            ps3.setInt(2,batchid);

                            int x3 = ps3.executeUpdate();
                            if(x3>0){
                                message = "Student Number Updated Successfully....";
                            }else{
                                throw new BatchException("Unable to update student");
                            }
                            flag = false;
                            break;
                        case 4:
                            System.out.println("Enter batch Start Date For Update (YYY-MM-DD) : ");
                            String date = sc.next();
                            PreparedStatement ps4 = conn.prepareStatement("UPDATE BATCH SET BatchStartDate = ? WHERE  BatchID = ?");
                            ps4.setString(1,date);
                            ps4.setInt(2,batchid);
                            int x4 = ps4.executeUpdate();
                            if(x4 > 0){
                                message = "Batch Start Date Updated Successfully...";
                            }else {
                                throw new BatchException("Unable to update batch start date ");
                            }
                            flag = false;
                            break;
                        case 5:
                            System.out.println("Enter Updated Duration : ");
                            String duration = sc.next();
                            PreparedStatement ps5 = conn.prepareStatement("UPDATE Batch SET Duration = ? WHERE BatchID = ?");
                            ps5.setString(1,duration);
                            ps5.setInt(2,batchid);
                            int x5 = ps5.executeUpdate();
                            if(x5>0){
                                message = "Duration updated successfully";
                            }else{
                                throw new BatchException("Unable to update duration");
                            }
                            flag = false;
                            break;
                    }
                }
            }
        }catch(SQLException e){

        }
        return message;
    }

    @Override
    public String addBatch(Batch batch) throws BatchException {
        String message = "Unable added batch details";

        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Batch(CourseID,FacultyID," +
                                                              "NumberOfStudents,BatchStartDate,Duration) VALUES (?,?,?,?,?)");

            ps.setInt(1,batch.getCourseID());
            ps.setInt(2,batch.getFacultyID());
            ps.setInt(3,batch.getNumberOfStudent());
            ps.setString(4,batch.getBatchStartDate());
            ps.setString(5,batch.getDuration());


            int x = ps.executeUpdate();
            if(x>0){
                message = x+" Batch details updated successfully..";
            }else{
                throw new BatchException("Unable to added batch details");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new BatchException(e.getMessage());
        }
        return message;
    }

    @Override
    public String createFacultyID(Faculty faculty) throws FacultyException {
        String message = "Not Registered..";

        try(Connection con = DBUtility.provideConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO FACULTY(FacultyName,FacultyAddress,Mobile,Email,Username,Password) VALUES (?,?,?,?,?,?)");
            ps.setString(1,faculty.getFacultyName());
            ps.setString(2,faculty.getFacultyAddress());
            ps.setString(3,faculty.getMobile());
            ps.setString(4,faculty.getEmail());
            ps.setString(5,faculty.getUsername());
            ps.setString(6,faculty.getPassword());


            int x = ps.executeUpdate();
            if(x>0){
                message = x+" Faculty Registered Successfully";
            }else{
                throw new FacultyException("Registration Failed due to lack of details");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new FacultyException(e.getMessage());
        }
        return message;
    }
}
