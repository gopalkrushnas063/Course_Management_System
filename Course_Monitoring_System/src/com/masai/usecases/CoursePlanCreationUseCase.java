package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.CoursePlanException;
import com.masai.model.CoursePlan;

import java.util.Scanner;

public class CoursePlanCreationUseCase {
    public static void coursePlanCreation(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Batch ID :");
        int bid = sc.nextInt();
        System.out.println("Enter Day Number :");
        int day = sc.nextInt();
        System.out.println("Enter Course Topic :");
        String topic = sc.next();
        System.out.println("Enter Course Status : ");
        sc.nextLine();
        String status = sc.nextLine();

        AdminDao dao = new AdminDaoImpl();
        CoursePlan coursePlan = new CoursePlan();
        coursePlan.setBatchid(bid);
        coursePlan.setDaynumber(day);
        coursePlan.setTopic(topic);
        coursePlan.setStatus(status);


        try{
            String result = dao.createCoursePlan(coursePlan);
            System.out.println(result);
        }catch (CoursePlanException e){
            System.out.println(e.getMessage());
        }
    }
}
