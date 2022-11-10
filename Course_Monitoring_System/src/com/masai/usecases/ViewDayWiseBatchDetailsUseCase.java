package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.DayWiseBatchDetailsException;
import com.masai.model.DayWiseBatchDetails;

import java.util.List;
import java.util.Scanner;

public class ViewDayWiseBatchDetailsUseCase {
    public static void viewBatchDetailsDayWise(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter day number to get batch details : ");
        int day = sc.nextInt();
        System.out.println("+--------------------------------------------------------------------------+\n" +
                          "|                         View Day-Wise Batch Details                       |\n" +
                          "+---------------------------------------------------------------------------+");
        AdminDao dao = new AdminDaoImpl();
        try{
            List<DayWiseBatchDetails> batchDetails = dao.dayWiseDetails(day);
            batchDetails.forEach(b->{
                System.out.println("| Batch ID           : "+b.getBatchID());
                System.out.println("| Day Number         : "+b.getDayNumber());
                System.out.println("| Course Name        : "+b.getCourseName());
                System.out.println("| Faculty Name       : "+b.getFacultyName());
                System.out.println("| Number Of Students : "+b.getNumberOfStudents());
                System.out.println("| Status             : "+b.getStatus());
                System.out.println("+---------------------------------------------------------------------------+");
            });
        }catch (DayWiseBatchDetailsException e){
            System.out.println(e.getMessage());
        }
    }
}
