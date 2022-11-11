package com.masai.app_execute_section;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.dao.FacultyDao;
import com.masai.dao.FacultyDaoImpl;
import com.masai.exception.AdminException;
import com.masai.exception.FacultyException;
import com.masai.usecases.*;

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
        System.out.println("| 1.  Create Course                                                        |");
        System.out.println("| 2.  Update Course                                                        |");
        System.out.println("| 3.  View Course                                                          |");
        System.out.println("| 4.  Create Batch                                                         |");
        System.out.println("| 5.  Update Batch Details                                                 |");
        System.out.println("| 6.  View Batch Details By Course Name                                    |");
        System.out.println("| 7.  Register Faculty                                                     |");
        System.out.println("| 8.  Update Faculty                                                       |");
        System.out.println("| 9.  View Faculty Details                                                 |");
        System.out.println("| 10. Create Course Plan                                                   |");
        System.out.println("| 11. Update Course Plan                                                   |");
        System.out.println("| 12. View Course Plan                                                     |");
        System.out.println("| 13. View the Day wise update of every batch                              |");
        System.out.println("| 14. Generate report for every batch                                      |");
        System.out.println("| 15. Logout                                                               |");
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
                AddCourseDetailsUseCase.addCourse();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 2:
                UpdateCourseDetailsUseCase.updateCourse();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 3:
                ViewCoursesUseCase.viewCourse();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 4:
                AddBatchDetailsUseCase.addBatch();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 5:
                UpdateBatchDetailsUseCase.updateBatch();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 6:
                ViewBatchDetailsUseCase.viewBatch();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 7:
                RegisterFacultyUseCase.registerFaculty();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 8:
                UpdateFacultyDetailsUseCase.updateFaculty();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 9:
                ViewFacultyDetailsUseCase.viewFacultyDetails();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 10:
                CoursePlanCreationUseCase.coursePlanCreation();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 11:
                UpdateCoursePlanUseCase.updateCoursePlan();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 12:
                ViewCoursePlanDetailsUseCase.viewCourseplan();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 13:
                ViewDayWiseBatchDetailsUseCase.viewBatchDetailsDayWise();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 14:
                ViewBatchWiseReportUseCase.viewBatchWiseReport();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 15:
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| Admin Logout Successfully                                                |");
                System.out.println("+--------------------------------------------------------------------------+");
                UserActivities.selectUser();
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
