package com.masai.usecases.course_plan;

import com.masai.app_execute_section.BackAndExitOperationAdmin;
import com.masai.app_execute_section.BackAndExitOperationFaculty;
import com.masai.app_execute_section.UserActivities;
import com.masai.usecases.CoursePlanCreationUseCase;
import com.masai.usecases.UpdateCoursePlanUseCase;
import com.masai.usecases.ViewCoursePlanDetailsUseCase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoursePlanUseCase {
    public static void coursePlan(){
        Scanner sc = new Scanner(System.in);

        System.out.println("+-========================================================================-+");
        System.out.println("| 1.  Create Course Plan                                                   |");
        System.out.println("| 2.  Update Course Plan                                                   |");
        System.out.println("| 3.  View Course Plan                                                     |");
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
                CoursePlanCreationUseCase.coursePlanCreation();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 2:
                UpdateCoursePlanUseCase.updateCoursePlan();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 3:
                ViewCoursePlanDetailsUseCase.viewCourseplan();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
        }
    }
}
