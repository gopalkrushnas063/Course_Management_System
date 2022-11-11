package com.masai.usecases;

import com.masai.dao.FacultyDao;
import com.masai.dao.FacultyDaoImpl;
import com.masai.exception.CoursePlanException;
import com.masai.model.CoursePlan;
import com.masai.model.FacultyCourseView;

import java.util.List;
import java.util.Scanner;

public class FacultyCoursePlanViewUseCase {
    public static void facultyCoursePlanView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Faculty ID to get course plan :");
        int fid = sc.nextInt();
        FacultyDao dao = new FacultyDaoImpl();
        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                          View Course Plan Details                        |\n" +
                           "+--------------------------------------------------------------------------+");

        try{
            List<FacultyCourseView> coursePlans = dao.viewCoursePlan(fid);
            coursePlans.forEach(f->{
                System.out.println("Faculty ID       : "+f.getFacultyID());
                System.out.println("Faculty Name     : "+f.getFacultyName());
                System.out.println("Batch ID         : "+f.getBatchID());
                System.out.println("Batch Start Date : "+f.getBatchStartDay());
                System.out.println("Topic            : "+f.getTopic());
                System.out.println("Status           : "+f.getStatus());
                System.out.println("+--------------------------------------------------------------------------+");
            });
        }catch (CoursePlanException e){
            System.out.println(e.getMessage());
        }
    }
}
