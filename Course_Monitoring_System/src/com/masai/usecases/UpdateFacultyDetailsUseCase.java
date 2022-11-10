package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.FacultyException;

import java.util.Scanner;

public class UpdateFacultyDetailsUseCase {
    public static void updateFaculty(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Faculty ID for update the faculty details : ");
        int fid = sc.nextInt();

        AdminDao dao = new AdminDaoImpl();

        try{
            String result = dao.updateFaculty(fid);
            System.out.println(result);
        }catch (FacultyException e){
            System.out.println(e.getMessage());
        }
    }
}
