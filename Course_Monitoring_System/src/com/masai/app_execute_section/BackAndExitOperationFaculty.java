package com.masai.app_execute_section;

import java.util.Scanner;

public class BackAndExitOperationFaculty {
    public static void backAndExitFaculty(){
        Scanner sc = new Scanner(System.in);
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println("| 0. Go Back                                                               |");
        System.out.println("| 9. Logout                                                                |");
        System.out.println("+--------------------------------------------------------------------------+");


        int choice = sc.nextInt();

        switch (choice){
            case 0:
                UserActivities.faculty();
                break;
            case 9:
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| Admin Logout Successfully                                                |");
                System.out.println("+--------------------------------------------------------------------------+");
                UserActivities.selectUser();
        }
    }
}
