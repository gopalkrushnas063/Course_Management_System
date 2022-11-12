package com.masai.usecases;

import com.masai.dao.FacultyDao;
import com.masai.dao.FacultyDaoImpl;
import com.masai.exception.FacultyException;

import java.util.Scanner;

public class FacultyUsernameOrPasswordChangeUseCase {
    public static void facultyPwdUnameChange(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Faculty ID for change your password or username :");
        int fid = sc.nextInt();

        FacultyDao dao = new FacultyDaoImpl();

        try{
            String result = dao.facultyPasswordUpdate(fid);
            System.out.println(result);
        }catch (FacultyException e){
            System.out.println("Enter valid faculty id");
        }
    }
}
