package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.CourseException;
import com.masai.model.CoursePlan;

import java.util.ArrayList;
import java.util.List;

public class ViewCoursePlanDetailsUseCase {
    public static void viewCourseplan(){
        AdminDao dao = new AdminDaoImpl();

        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                          View Course Plan Details                        |\n" +
                           "+--------------------------------------------------------------------------+");
        try{
            List<CoursePlan> coursePlans = dao.viewCoursePlanList();
            coursePlans.forEach(c->{
                System.out.println("| Plan ID    : "+c.getPlanid());
                System.out.println("| Batch ID   : "+c.getBatchid());
                System.out.println("| Day Number : "+c.getDaynumber());
                System.out.println("| Topic      : "+c.getTopic());
                System.out.println("| Status     : "+c.getStatus());
                System.out.println("+--------------------------------------------------------------------------+");
            });
        }catch (CourseException e){
            System.out.println(e.getMessage());
        }
    }
}
