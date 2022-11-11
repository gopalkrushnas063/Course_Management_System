package com.masai.usecases.course;

import com.masai.app_execute_section.BackAndExitOperationAdmin;
import com.masai.app_execute_section.BackAndExitOperationFaculty;
import com.masai.app_execute_section.UserActivities;
import com.masai.usecases.AddCourseDetailsUseCase;
import com.masai.usecases.UpdateCourseDetailsUseCase;
import com.masai.usecases.ViewCoursesUseCase;
import com.masai.usecases.course_plan.CoursePlanUseCase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseUseCase {
    public static void courseOps(){
        Scanner sc = new Scanner(System.in);

        System.out.println("+-========================================================================-+");
        System.out.println("| 1.  Create Course                                                        |");
        System.out.println("| 2.  Update Course                                                        |");
        System.out.println("| 3.  View Course                                                          |");
        System.out.println("| 4.  Course Plan                                                          |");
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
                CoursePlanUseCase.coursePlan();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
        }
        UserActivities.admin();
    }
}
