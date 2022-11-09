package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.CourseException;

import java.util.Scanner;

public class UpdateCourseDetailsUseCase {
    public static void updateCourse(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Course ID for update course details : ");
        int cid = sc.nextInt();

        AdminDao dao = new AdminDaoImpl();
        try{
            String result = dao.updateCourse(cid);
            System.out.println(result);
        }catch (CourseException e){
            System.out.println(e.getMessage());
        }
    }
}
