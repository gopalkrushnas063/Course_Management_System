package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.BatchWiseException;
import com.masai.model.BatchWiseDetails;

import java.util.List;

public class ViewBatchWiseReportUseCase {
    public static void viewBatchWiseReport(){

        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                           View Batch Wise Report                         |\n" +
                           "+--------------------------------------------------------------------------+");

        AdminDao dao = new AdminDaoImpl();
        try{
            List<BatchWiseDetails> batchWiseDetails = dao.batchWiseDetails();
            batchWiseDetails.forEach(b ->{
                System.out.println("| Batch ID           : "+b.getBatchId());
                System.out.println("| Faculty ID         : "+b.getFacultyID());
                System.out.println("| Faculty Name       : "+b.getFacultyName());
                System.out.println("| Course ID          : "+b.getCourseID());
                System.out.println("| Course Name        : "+b.getCourseName());
                System.out.println("| Course Fee         : "+b.getFee());
                System.out.println("| Number Of Students : "+b.getNumberOfStudent());
                System.out.println("| Batch Start Date   : "+b.getBatchStartDate());
                System.out.println("| Course Description : "+b.getCourseDesc());
                System.out.println("+--------------------------------------------------------------------------+");
            });
        }catch (BatchWiseException e){
            System.out.println(e.getMessage());
        }
    }
}
