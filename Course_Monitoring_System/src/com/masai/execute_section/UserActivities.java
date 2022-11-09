package com.masai.execute_section;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.AdminException;
import com.masai.usecases.AddCourseDetailsUseCase;
import com.masai.usecases.UpdateCourseDetailsUseCase;
import com.masai.usecases.ViewCoursesUseCase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserActivities {
    public static void selectUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose an options - \n" + "1. Admin Login\n" + "2. Faculty Login");

        System.out.println("------------------------------------------------");

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
                UserActivities.faculty();
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

        System.out.println("1.  Create Course" +'\n'+
                           "2.  Update Course" +'\n'+
                           "3.  View Course" +'\n'+
                           "4.  Create Batch" +'\n'+
                           "5.  Update Batch Details" +'\n'+
                           "6.  View Batch Details By Course Name" +'\n'+
                           "7.  Register Faculty" +'\n'+
                           "8.  Update Faculty" +'\n'+
                           "9.  View Faculty Details" +'\n'+
                           "10. Create Course Plan" +'\n'+
                           "11. Update Course Plan" +'\n'+
                           "12. View Course Plan" +'\n'+
                           "13. View the Day wise update of every batch" +'\n'+
                           "14. Generate report for every batch" +'\n'+
                           "15. Logout" +'\n'+
                           "----------------------------------------------");

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
                break;
            case 2:
                UpdateCourseDetailsUseCase.updateCourse();
                break;
            case 3:
                ViewCoursesUseCase.viewCourse();
                break;

        }
    }

    public static void faculty(){

    }
}
