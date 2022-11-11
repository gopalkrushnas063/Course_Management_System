package com.masai.dao;

import com.masai.exception.*;
import com.masai.model.*;
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
                    System.out.println("+--------------------------------------------------------------------------+");
                    System.out.println("|    Enter your choice :                                                   |");
                    System.out.println("+--------------------------------------------------------------------------+");
                    System.out.println("| 1. Course Name.                                                          |");
                    System.out.println("| 2. Course Fee.                                                           |");
                    System.out.println("| 3. Course Description.                                                   |");
                    System.out.println("+--------------------------------------------------------------------------+");

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
                    System.out.println("+--------------------------------------------------------------------------+");
                    System.out.println("|    Enter your choice :                                                   |");
                    System.out.println("+--------------------------------------------------------------------------+");
                    System.out.println("| 1. Update Course ID.                                                     |");
                    System.out.println("| 2. Update Faculty ID.                                                    |");
                    System.out.println("| 3. Update Number Of Students.                                            |");
                    System.out.println("| 4. Update Batch Start Date.                                              |");
                    System.out.println("| 5. Update Duration.                                                      |");
                    System.out.println("+--------------------------------------------------------------------------+");

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
    public List<Batch> viewBatch() throws BatchException {
        List<Batch> batches = new ArrayList<>();

        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM BATCH");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Batch batch = new Batch();
                batch.setBatchID(rs.getInt("BatchID"));
                batch.setCourseID(rs.getInt("CourseID"));
                batch.setFacultyID(rs.getInt("FacultyID"));
                batch.setNumberOfStudent(rs.getInt("NumberOfStudents"));
                batch.setBatchStartDate(rs.getString("BatchStartDate"));
                batch.setDuration(rs.getString("Duration"));

                batches.add(batch);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        if(batches.size() == 0){
            throw new BatchException("No any record found");
        }
        return batches;

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

    @Override
    public String updateFaculty(int fid) throws FacultyException {
        String message = "Something went wrong , unable to update faculty details..";
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT FacultyID FROM Faculty WHERE FacultyId = ?");
            ps.setInt(1,fid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                while (flag){
                    System.out.println("+--------------------------------------------------------------------------+");
                    System.out.println("|    Enter your choice :                                                   |");
                    System.out.println("+--------------------------------------------------------------------------+");
                    System.out.println("| 1. Update Faculty Name.                                                  |");
                    System.out.println("| 2. Update Faculty Address.                                               |");
                    System.out.println("| 3. Update Mobile Number.                                                 |");
                    System.out.println("| 4. Update Email ID.                                                      |");
                    System.out.println("| 5. Update Username.                                                      |");
                    System.out.println("| 6. Update Password.                                                      |");
                    System.out.println("+--------------------------------------------------------------------------+");
                    int choice = sc.nextInt();
                    switch (choice){
                        case 1:
                            System.out.println("Enter Faculty Name For Update: ");
                            String fname = sc.next();
                            PreparedStatement ps1 = conn.prepareStatement("UPDATE FACULTY SET FacultyName = ? WHERE FacultyID = ?");
                            ps1.setString(1,fname);
                            ps1.setInt(2,fid);

                            int x = ps1.executeUpdate();

                            if(x>0){
                                message = "Faculty name updated successfully .";
                            }else{
                                throw new FacultyException("Something went wrong to update faculty name. Please try again");
                            }
                            flag = false;
                            break;
                        case 2:
                            System.out.println("Enter faculty address for update: ");
                            String fadd = sc.next();

                            PreparedStatement ps2 = conn.prepareStatement("UPDATE FACULTY SET FacultyAddress = ? WHERE FacultyID = ?");
                            ps2.setString(1,fadd);
                            ps2.setInt(2,fid);

                            int x1 = ps2.executeUpdate();
                            if(x1>0){
                                message = "Faculty address updated successfully";
                            }else{
                                throw new FacultyException("Something went wrong to update faculty address");
                            }
                            flag = false;
                            break;
                        case 3:
                            System.out.println("Enter faculty mobile number for update :");
                            String mob = sc.next();
                            PreparedStatement ps3 = conn.prepareStatement("UPDATE FACULTY SET Mobile = ? WHERE FacultyID = ?");
                            ps3.setString(1,mob);
                            ps3.setInt(2,fid);

                            int x2 = ps3.executeUpdate();
                            if(x2>0){
                                message = "Faculty mobile number updated successfully";
                            }else{
                                throw new FacultyException("Something went wrong ,please try again");
                            }
                            flag = false;
                            break;
                        case 4:
                            System.out.println("Enter faculty Email ID for update");
                            String email= sc.next();
                            PreparedStatement ps4 = conn.prepareStatement("UPDATE FACULTY SET Email = ? WHERE FacultyID = ?");
                            ps4.setString(1,email);
                            ps4.setInt(2,fid);
                            int x3 = ps4.executeUpdate();
                            if(x3>0){
                                message = "Email ID updated successfully";
                            }else{
                                throw new FacultyException("Unable to update faculty Email ID.");
                            }
                            flag = false;
                            break;
                        case 5:
                            System.out.println("Enter username of faculty for update :");
                            String uname = sc.next();
                            PreparedStatement ps5 = conn.prepareStatement("UPDATE FACULTY SET Username = ? WHERE FacultyId = ?");
                            ps5.setString(1,uname);
                            ps5.setInt(2,fid);

                            int x4 = ps5.executeUpdate();
                            if(x4 > 0){
                                message = "Username updated successfully";
                            }else {
                                throw new FacultyException("Unable to update the faculty username");
                            }
                            flag = false;
                            break;
                        case 6:
                            System.out.println("Enter faculty password for update :");
                            String pwd = sc.next();
                            PreparedStatement ps6 = conn.prepareStatement("UPDATE FACULTY SET Password = ? WHERE FacultyId = ?");
                            ps6.setString(1,pwd);
                            ps6.setInt(2,fid);
                            int x5 = ps6.executeUpdate();

                            if(x5>0){
                                message = "Password updated successfully";
                            }else{
                                throw new FacultyException("Unable to update password");
                            }
                            flag = false;
                            break;
                        default:
                            System.out.println("Kindly choose the valid option for update the faculty details");
                            flag = true;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new FacultyException(e.getMessage());
        }

        return message;
    }

    @Override
    public List<Faculty> viewAllFacultyDetails() throws FacultyException {
        List<Faculty> faculties = new ArrayList<>();

        try (Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM FACULTY");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Faculty faculty = new Faculty();
                faculty.setFacultyID(rs.getInt("FacultyID"));
                faculty.setFacultyName(rs.getString("FacultyName"));
                faculty.setFacultyAddress(rs.getString("FacultyAddress"));
                faculty.setMobile(rs.getString("Mobile"));
                faculty.setEmail(rs.getString("Email"));
                faculty.setUsername(rs.getString("Username"));
                faculty.setPassword(rs.getString("Password"));

                faculties.add(faculty);
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new FacultyException(e.getMessage());
        }

        if(faculties.size() == 0){
            throw new FacultyException("Faculty data not found");
        }
        return faculties;
    }



    @Override
    public String createCoursePlan(CoursePlan coursePlan) throws CoursePlanException {
        String message = "Unable to Create Course Plan";

        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO CoursePlan(BatchID,DayNumber,Topic,Status) VALUES (?,?,?,?)");
            ps.setInt(1,coursePlan.getBatchid());
            ps.setInt(2,coursePlan.getDaynumber());
            ps.setString(3,coursePlan.getTopic());
            ps.setString(4,coursePlan.getStatus());

            int x = ps.executeUpdate();
            if(x>0){
                message = x+" Course updated successfully";
            }else{
                throw new CoursePlanException("Unable to course plan");
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new CoursePlanException(e.getMessage());
        }
        return message;
    }

    @Override
    public String updateCoursePlan(int planId) throws CoursePlanException {
        String message = "Unable to update course plan";
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT PlanID FROM CoursePlan WHERE PlanID = ?");
            ps.setInt(1,planId);
            ResultSet rs = ps.executeQuery();
            while (flag){
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("|    Enter your choice :                                                   |");
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| 1. Update Course Plan Batch ID.                                          |");
                System.out.println("| 2. Update Course Plan Day Number.                                        |");
                System.out.println("| 3. Update Course Plan Topic.                                             |");
                System.out.println("| 4. Update Course Plan Status.                                            |");
                System.out.println("+--------------------------------------------------------------------------+");

                int choice = sc.nextInt();
                if(rs.next()) {
                    switch (choice) {
                        case 1:
                            System.out.println("Enter Course Plan Batch ID For Update : ");
                            int bid = sc.nextInt();
                            PreparedStatement ps1 = conn.prepareStatement("UPDATE CoursePlan SET BatchID = ? WHERE PlanID = ?");
                            ps1.setInt(1, bid);
                            ps1.setInt(2, planId);

                            int x1 = ps1.executeUpdate();
                            if (x1 > 0) {
                                message = "Batch ID updated successfully ..";
                            } else {
                                throw new CoursePlanException("Unable to update Batch ID");
                            }
                            flag = false;
                            break;
                        case 2:
                            System.out.println("Enter Course Plan Day Number For Update : ");
                            int day = sc.nextInt();
                            PreparedStatement ps2 = conn.prepareStatement("UPDATE CoursePlan SET DayNumber = ? WHERE PlanID = ?");
                            ps2.setInt(1, day);
                            ps2.setInt(2, planId);

                            int x2 = ps2.executeUpdate();
                            if (x2 > 0) {
                                message = "Day Number updated successfully ..";
                            } else {
                                throw new CoursePlanException("Unable to update Day Number");
                            }
                            flag = false;
                            break;
                        case 3:
                            System.out.println("Enter Course Plan Topic For Update : ");
                            String topic = sc.next();
                            PreparedStatement ps3 = conn.prepareStatement("UPDATE CoursePlan SET Topic = ? WHERE PlanID = ?");
                            ps3.setString(1, topic);
                            ps3.setInt(2, planId);

                            int x3 = ps3.executeUpdate();
                            if (x3 > 0) {
                                message = "Topic updated successfully ..";
                            } else {
                                throw new CoursePlanException("Unable to update Topic");
                            }
                            flag = false;
                            break;
                        case 4:
                            System.out.println("Enter Course Plan Status For Update : ");
                            String status = sc.next();
                            PreparedStatement ps4 = conn.prepareStatement("UPDATE CoursePlan SET Status = ? WHERE PlanID = ?");
                            ps4.setString(1, status);
                            ps4.setInt(2, planId);

                            int x4 = ps4.executeUpdate();
                            if (x4 > 0) {
                                message = "Status updated successfully ..";
                            } else {
                                throw new CoursePlanException("Unable to update Status");
                            }
                            flag = false;
                            break;
                        default:
                            System.out.println("Enter valid option for update the course plan");
                            flag = true;
                    }
                }else{
                    throw new CoursePlanException("Enter valid PlanID for update course plan ");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new CoursePlanException(e.getMessage());
        }

        return message;
    }

    @Override
    public List<CoursePlan> viewCoursePlanList() throws CourseException {
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
                CoursePlan coursePlan = new CoursePlan(p,b,d,t,s);
                coursePlans.add(coursePlan);

            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new CourseException(e.getMessage());
        }

        if(coursePlans.size()==0){
            throw new CourseException("No any course plan found");
        }
        return coursePlans;
    }

    @Override
    public List<DayWiseBatchDetails> dayWiseDetails(int day) throws DayWiseBatchDetailsException {
        List<DayWiseBatchDetails> dayWiseBatchDetails = new ArrayList<>();

        try (Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT b.BatchID,cp.DayNumber,c.Course_Name,f.FacultyName,b.NumberOfStudents,cp.Status FROM Faculty f INNER JOIN Batch b ON f.FacultyID = b.FacultyID INNER JOIN Course c ON c.CourseID = b.CourseID INNER JOIN CoursePlan cp ON b.BatchID = cp.BatchID WHERE cp.DayNumber = ?");
            ps.setInt(1,day);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int b = rs.getInt("BatchID");
                int d = rs.getInt("DayNumber");
                String c = rs.getString("Course_Name");
                String f = rs.getString("FacultyName");
                int n = rs.getInt("NumberOfStudents");
                String s = rs.getString("Status");
                DayWiseBatchDetails dayWiseBatchDetails1 = new DayWiseBatchDetails(b,d,c,f,n,s);
                dayWiseBatchDetails.add(dayWiseBatchDetails1);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DayWiseBatchDetailsException(e.getMessage());
        }

        if(dayWiseBatchDetails.size() == 0){
            throw new DayWiseBatchDetailsException("No any record found");
        }

        return dayWiseBatchDetails;
    }

    @Override
    public List<BatchWiseDetails> batchWiseDetails() throws BatchWiseException {
        List<BatchWiseDetails> batchWiseDetails = new ArrayList<>();

        try(Connection conn = DBUtility.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT b.BatchID,f.FacultyID,f.FacultyName,c.CourseID,c.Course_Name,c.Fee,b.NumberOfStudents,b.BatchStartDate,c.Course_Description FROM Batch b INNER JOIN Course c INNER JOIN Faculty f ON b.CourseID = c.CourseID AND b.FacultyID = f.FacultyID GROUP BY BatchID ORDER BY BatchID");

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int bid = rs.getInt("batchid");
                int fid = rs.getInt("facultyid");
                String fn = rs.getString("facultyname");
                int cid = rs.getInt("courseid");
                String cn = rs.getString("course_name");
                int fee = rs.getInt("fee");
                int nos = rs.getInt("numberofstudents");
                String bsd = rs.getString("batchstartdate");
                String cd = rs.getString("course_description");
                BatchWiseDetails bdw = new BatchWiseDetails(bid,fn,fid,cid,cn,fee,bsd,nos,cd);
                batchWiseDetails.add(bdw);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new BatchWiseException(e.getMessage());
        }

        if(batchWiseDetails.size()==0){
            throw new BatchWiseException("No any record found");
        }


        return batchWiseDetails;
    }
}
