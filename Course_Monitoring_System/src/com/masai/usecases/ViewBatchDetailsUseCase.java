package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.BatchException;
import com.masai.model.Batch;

import java.util.List;
import java.util.Scanner;

public class ViewBatchDetailsUseCase {
    public static void viewBatch(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                        View Registered Batch Details                     |\n" +
                           "+--------------------------------------------------------------------------+");


        try{
            List<Batch> batch = dao.viewBatch();
            batch.forEach(b ->{
                System.out.println("| Batch ID           : "+b.getBatchID());
                System.out.println("| Course ID          : "+b.getCourseID());
                System.out.println("| Faculty ID         : "+b.getFacultyID());
                System.out.println("| Number Of Students : "+b.getNumberOfStudent());
                System.out.println("| Batch Start Date   : "+b.getBatchStartDate());
                System.out.println("| Duration           : "+b.getDuration()+" Months");
                System.out.println("----------------------------------------------------------------------------");
            });
        }catch (BatchException e){
            System.out.println(e.getMessage());
        }
    }
}
