package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.CoursePlanException;

import java.util.Scanner;

public class UpdateCoursePlanUseCase {
    public static void updateCoursePlan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Plan ID For Update The Course Plan : ");
        int pid = sc.nextInt();

        AdminDao dao = new AdminDaoImpl();

        try {
            String result = dao.updateCoursePlan(pid);
            System.out.println(result);
        }catch (CoursePlanException e){
            System.out.println(e.getMessage());
        }
    }
}
