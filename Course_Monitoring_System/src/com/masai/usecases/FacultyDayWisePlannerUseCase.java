package com.masai.usecases;

import com.masai.dao.FacultyDao;
import com.masai.dao.FacultyDaoImpl;
import com.masai.exception.FacultyException;
import com.masai.model.CoursePlan;

import java.util.Scanner;

public class FacultyDayWisePlannerUseCase {
    public static void facultyDayWisePlan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Fill up the day wise planner form : ");
        System.out.println("Enter Batch ID : ");
        int bid = sc.nextInt();
        System.out.println("Enter Day Number : ");
        int day = sc.nextInt();
        System.out.println("Enter Topic : ");
        sc.nextLine();
        String topic = sc.nextLine();
        System.out.println("Enter Current Status :");
        String status = sc.nextLine();

        CoursePlan coursePlan = new CoursePlan();
        coursePlan.setBatchid(bid);
        coursePlan.setDaynumber(day);
        coursePlan.setTopic(topic);
        coursePlan.setStatus(status);

        FacultyDao dao = new FacultyDaoImpl();
        try {
            String result = dao.dayWisePlan(coursePlan);
            System.out.println(result);
        }catch (FacultyException e){
            System.out.println("Something went wrong...");
        }
    }
}
