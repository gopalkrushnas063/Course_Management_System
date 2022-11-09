package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.BatchException;
import com.masai.model.Batch;

import java.util.Scanner;

public class AddBatchDetailsUseCase {
    public static void addBatch(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Course ID : ");
        int cid = sc.nextInt();
        System.out.println("Enter Faculty ID : ");
        int fid = sc.nextInt();
        System.out.println("Enter Number Of Students : ");
        int numOfStudent = sc.nextInt();
        System.out.println("Enter Batch Start Date(YYYY-DD-MM) : ");
        String date = sc.next();
        System.out.println("Enter Course Duration (In Month : MM) : ");
        String duration = sc.next();

        Batch batch = new Batch();
        batch.setCourseID(cid);
        batch.setFacultyID(fid);
        batch.setNumberOfStudent(numOfStudent);
        batch.setBatchStartDate(date);
        batch.setDuration(duration);

        AdminDao dao = new AdminDaoImpl();
        try {
            String result = dao.addBatch(batch);
            System.out.println(result);
        }catch (BatchException e){
            e.printStackTrace();
        }

    }
}
