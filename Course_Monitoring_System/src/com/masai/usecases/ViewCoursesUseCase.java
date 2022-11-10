package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.CourseException;
import com.masai.model.Course;

import java.util.List;

public class ViewCoursesUseCase {
    public static void viewCourse(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                       View Registered Course Details                     |\n" +
                           "+--------------------------------------------------------------------------+");

        try{
            List<Course> courses = dao.viewCourse();
            
            courses.forEach(s ->{
            	
                System.out.println("| Course ID          : "+s.getCourseID());
                System.out.println("| Course Name        : "+s.getCourseName());
                System.out.println("| Course Fee         : "+s.getFee());
                System.out.println("| Course Description : "+s.getCourseDescription());
                System.out.println("+---------------------------------------------------------------------------");

            });
        }catch (CourseException e){
            System.out.println(e.getMessage());
        }
    }
}
