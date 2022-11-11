package com.masai.usecases;

import com.masai.dao.FacultyDao;
import com.masai.dao.FacultyDaoImpl;
import com.masai.exception.CoursePlanException;
import com.masai.model.CoursePlan;

import java.util.List;

public class FacultyCoursePlanViewUseCase {
    public static void facultyCoursePlanView(){
        FacultyDao dao = new FacultyDaoImpl();
        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                          View Course Plan Details                        |\n" +
                           "+--------------------------------------------------------------------------+");

        try{
            List<CoursePlan> coursePlans = dao.viewCoursePlan();
            coursePlans.forEach(c->{
                System.out.println("Plan ID    : "+c.getPlanid());
                System.out.println("Batch ID   : "+c.getBatchid());
                System.out.println("Day Number : "+c.getDaynumber());
                System.out.println("Topic      : "+c.getTopic());
                System.out.println("Status     : "+c.getStatus());
                System.out.println("+--------------------------------------------------------------------------+");
            });
        }catch (CoursePlanException e){
            System.out.println(e.getMessage());
        }
    }
}
