package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.AdminException;

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

        System.out.println("1. Create Course" +'\n'+
                           "2. Update Course" +'\n'+
                           "3. View Course");
    }

    public static void faculty(){

    }
}
