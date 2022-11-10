package com.masai.usecases;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.FacultyException;
import com.masai.model.Faculty;

import java.util.List;

public class ViewFacultyDetailsUseCase {
    public static void viewFacultyDetails(){
        AdminDao dao = new AdminDaoImpl();

        System.out.println("+--------------------------------------------------------------------------+\n" +
                           "|                        View All Faculties Details                        |\n" +
                           "+--------------------------------------------------------------------------+");
        try{
            List<Faculty> faculties = dao.viewAllFacultyDetails();
            faculties.forEach(f->{
                System.out.println("Faculty ID            : "+f.getFacultyID());
                System.out.println("Faculty Name          : "+f.getFacultyName());
                System.out.println("Faculty Address       : "+f.getFacultyAddress());
                System.out.println("Faculty Mobile Number : "+f.getMobile());
                System.out.println("Faculty Email ID      : "+f.getEmail());
                System.out.println("Faculty Username      : "+f.getUsername());
                System.out.println("Faculty password      : "+f.getPassword());
                System.out.println("+--------------------------------------------------------------------------+");
            });
        }catch (FacultyException e){
            System.out.println(e.getMessage());
        }
    }
}
