package com.masai.usecases.faculty;

import com.masai.app_execute_section.BackAndExitOperationAdmin;
import com.masai.app_execute_section.BackAndExitOperationFaculty;
import com.masai.app_execute_section.UserActivities;
import com.masai.usecases.RegisterFacultyUseCase;
import com.masai.usecases.UpdateFacultyDetailsUseCase;
import com.masai.usecases.ViewFacultyDetailsUseCase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FacultyUseCase {
    public static void facultyUseCase(){
        Scanner sc = new Scanner(System.in);

        System.out.println("+-========================================================================-+");
        System.out.println("| 1.  Register Faculty                                                     |");
        System.out.println("| 2.  Update Faculty                                                       |");
        System.out.println("| 3.  View Faculty Details                                                 |");
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
                RegisterFacultyUseCase.registerFaculty();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 2:
                UpdateFacultyDetailsUseCase.updateFaculty();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 3:
                ViewFacultyDetailsUseCase.viewFacultyDetails();
                BackAndExitOperationAdmin.backAndExitOps();
                break;

        }
        UserActivities.admin();
    }
}
