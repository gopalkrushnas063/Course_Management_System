package com.masai.app_execute_section;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.dao.FacultyDao;
import com.masai.dao.FacultyDaoImpl;
import com.masai.exception.AdminException;
import com.masai.exception.FacultyException;
import com.masai.model.CoursePlan;
import com.masai.usecases.*;
import com.masai.usecases.batch.BatchUseCase;
import com.masai.usecases.course.CourseUseCase;
import com.masai.usecases.course_plan.CoursePlanUseCase;
import com.masai.usecases.faculty.FacultyUseCase;
import com.masai.utilities.ThankYou;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserActivities {
    public static void selectUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                          Course Monitoring System                        |\n" +
                           "+--------------------------------------------------------------------------+");
        System.out.println("|  Choose an options :                                                     |");
        System.out.println("|  1. Admin Login                                                          |");
        System.out.println("|  2. Faculty Login                                                        |");
        System.out.println("+--------------------------------------------------------------------------+");

        int choice = 0;
        try{
            choice = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.println("Try Again.......!");
            UserActivities.selectUser();
        }
        switch (choice) {
            case 1:
                System.out.println("Enter admin username");
                String username=sc.next();
                System.out.println("Enter admin password");
                String password=sc.next();
                AdminDao dao = new AdminDaoImpl();
                try {
                    boolean adminAuth = dao.adminLogin(username,password);
                    if(adminAuth) {
                        UserActivities.admin();
                    }else {
                        UserActivities.selectUser();
                    }
                } catch (AdminException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("Enter faculty username : ");
                String uname=sc.next();
                System.out.println("Enter faculty password : ");
                String pwd=sc.next();
                System.out.println("+--------------------------------------------------------------------------+");
                FacultyDao fdao = new FacultyDaoImpl();
                try {
                    boolean facultyAuth = fdao.facultyLogin(uname,pwd);
                    if(facultyAuth) {
                        UserActivities.faculty();
                    }else {
                        UserActivities.selectUser();
                    }
                } catch (FacultyException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Invalid Choice...!" +'\n'+
                                   ".................." +'\n'+
                                   "Try Again !");
                UserActivities.selectUser();
        }

    }

    public static void admin(){
        Scanner sc = new Scanner(System.in);

        System.out.println("+-========================================================================-+");
        System.out.println("| 1.  Courses                                                              |");
        System.out.println("| 2.  Batch                                                                |");
        System.out.println("| 3.  Faculty                                                              |");
        System.out.println("| 4.  Course Plan                                                          |");
        System.out.println("| 5.  View Day wise Planner                                                |");
        System.out.println("| 6.  Generate report for every batch                                      |");
        System.out.println("| 7.  Logout                                                               |");
        System.out.println("| 8.  Exit From The App                                                    |");
        System.out.println("+-========================================================================-+");

        int choice = 0;
        try{
            choice = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Invalid input..!");
            System.out.println("Try Again...");
            UserActivities.admin();
        }
        switch (choice){
            case 1:
                CourseUseCase.courseOps();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 2:
                BatchUseCase.batchUseCase();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 3:
                FacultyUseCase.facultyUseCase();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 4:
                CoursePlanUseCase.coursePlan();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 5:
                ViewDayWiseBatchDetailsUseCase.viewBatchDetailsDayWise();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 6:
                ViewBatchWiseReportUseCase.viewBatchWiseReport();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 7:
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| Admin Logout Successfully                                                |");
                System.out.println("+--------------------------------------------------------------------------+");
                UserActivities.selectUser();
            case 8:
                ThankYou.thanks();
                UserActivities.selectUser();
                break;
        }
        UserActivities.admin();
    }

    public static void faculty(){
        Scanner sc = new Scanner(System.in);

        System.out.println("+-========================================================================-+");
        System.out.println("| 1.  View The Course Plan                                                 |");
        System.out.println("| 2.  Fill-up The Day wise Plan                                            |");
        System.out.println("| 3.  Update your password                                                 |");
        System.out.println("| 4.  Logout                                                               |");
        System.out.println("+-========================================================================-+");
        int choice = 0;
        try{
            choice = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Invalid input..!");
            System.out.println("Try Again...");
            UserActivities.admin();
        }
        switch (choice){
            case 1:
                FacultyCoursePlanViewUseCase.facultyCoursePlanView();
                BackAndExitOperationFaculty.backAndExitFaculty();
                break;
            case 4:
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| Faculty Logout Successfully                                              |");
                System.out.println("+--------------------------------------------------------------------------+");
                UserActivities.selectUser();
        }
        UserActivities.faculty();
    }
}
