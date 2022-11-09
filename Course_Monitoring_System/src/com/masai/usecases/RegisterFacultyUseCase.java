package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.FacultyException;
import com.masai.model.Faculty;

import java.util.Scanner;

public class RegisterFacultyUseCase {
    public static void registerFaculty(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Faculty Name :");
        String fname = sc.next();

        System.out.println("Enter Faculty Address :");
        sc.nextLine();
        String faddress = sc.nextLine();

        System.out.println("Enter Faculty Mobile Number :");
        String mobile = sc.next();

        System.out.println("Enter Faculty Email :");
        String email = sc.next();

        System.out.println("Enter Username :");
        String uname = sc.next();

        System.out.println("Enter Password :");
        String pwd = sc.next();

        Faculty faculty = new Faculty();
        faculty.setFacultyName(fname);
        faculty.setFacultyAddress(faddress);
        faculty.setMobile(mobile);
        faculty.setEmail(email);
        faculty.setUsername(uname);
        faculty.setPassword(pwd);

        AdminDao dao = new AdminDaoImpl();

        try {
            String result = dao.createFacultyID(faculty);
            System.out.println(result);
        }catch (FacultyException e){
            e.getMessage();
        }

    }
}
