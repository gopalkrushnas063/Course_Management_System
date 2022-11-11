package com.masai.app_execute_section;

import com.masai.usecases.course.CourseUseCase;

import java.util.Scanner;

public class BackCourseSection {
    public static void backCourseSection(){
        Scanner sc = new Scanner(System.in);
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println("| 0. Go Back                                                               |");
        System.out.println("| 1. Main Menu                                                             |");
        System.out.println("| 2. Logout                                                                |");
        System.out.println("+--------------------------------------------------------------------------+");


        int choice = sc.nextInt();

        switch (choice){
            case 0:
                CourseUseCase.courseOps();
                break;
            case 1:
                UserActivities.admin();
                break;
            case 2:
                System.out.println("+--------------------------------------------------------------------------+");
                System.out.println("| Admin Logout Successfully                                                |");
                System.out.println("+--------------------------------------------------------------------------+");
                UserActivities.selectUser();
        }
    }
}
