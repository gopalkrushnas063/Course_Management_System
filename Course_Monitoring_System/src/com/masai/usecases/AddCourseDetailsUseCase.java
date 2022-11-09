package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.CourseException;
import com.masai.model.Course;

import java.util.Scanner;

public class AddCourseDetailsUseCase {
    public static void addCourse() {
        Scanner sc = new Scanner(System.in);

        System.out.println("***** Enter Course Details *****");
        System.out.println("--------------------------------");
        System.out.println("Enter course name : ");
        String cname = sc.next();
        System.out.println("Enter course fee : ");
        int fee = sc.nextInt();
        System.out.println("Enter course description : ");
        String cdesc = sc.next();

        Course course = new Course();
        course.setCourseName(cname);
        course.setFee(fee);
        course.setCourseDescription(cdesc);

        AdminDao dao = new AdminDaoImpl();
        try{
            String result = dao.addCourse(course);
            System.out.println(result);
        }catch (CourseException e){
            e.printStackTrace();
        }

    }
}
