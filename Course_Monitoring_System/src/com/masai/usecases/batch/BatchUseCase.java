package com.masai.usecases.batch;

import com.masai.app_execute_section.BackAndExitOperationAdmin;
import com.masai.app_execute_section.BackAndExitOperationFaculty;
import com.masai.app_execute_section.UserActivities;
import com.masai.usecases.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BatchUseCase {
    public static void batchUseCase(){
        Scanner sc = new Scanner(System.in);

        System.out.println("+-========================================================================-+");
        System.out.println("| 1.  Create Batch                                                         |");
        System.out.println("| 2.  Update Batch                                                         |");
        System.out.println("| 3.  View Batch Details By Course Name                                    |");
        System.out.println("| 4.  View the Day wise update of every batch                              |");
        System.out.println("| 5.  Generate report for every batch                                      |");
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
                AddBatchDetailsUseCase.addBatch();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 2:
                UpdateBatchDetailsUseCase.updateBatch();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 3:
                ViewBatchDetailsUseCase.viewBatch();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 4:
                ViewDayWiseBatchDetailsUseCase.viewBatchDetailsDayWise();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
            case 5:
                ViewBatchWiseReportUseCase.viewBatchWiseReport();
                BackAndExitOperationAdmin.backAndExitOps();
                break;
        }
        UserActivities.admin();
    }
}
