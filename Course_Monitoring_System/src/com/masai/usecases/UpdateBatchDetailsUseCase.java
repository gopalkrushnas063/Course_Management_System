package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.BatchException;

import java.util.Scanner;

public class UpdateBatchDetailsUseCase {
    public static void updateBatch(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Batch ID for update the batch details according to your requirements : ");
        int bid = sc.nextInt();

        AdminDao dao = new AdminDaoImpl();
        try{
            String result = dao.updateBatch(bid);
            System.out.println(result);
        }catch (BatchException e){
            System.out.println(e.getMessage());
        }
    }
}
