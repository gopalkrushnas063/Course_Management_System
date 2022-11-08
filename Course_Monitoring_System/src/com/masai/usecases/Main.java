package com.masai.usecases;



import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.exception.AdminException;
import com.masai.model.Admin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean exit = false;
        while (!exit){

            System.out.println("==============================================");
            System.out.println("Welcome Course Monitoring System");
            System.out.println();
            System.out.println("1.Login as Admin");
            System.out.println("2.Login as Faculty");
            System.out.println("==============================================");

            AdminDao dao = new AdminDaoImpl();
            Scanner sc = new Scanner(System.in);
            int choose = sc.nextInt();
            boolean flag = true;
            if(choose == 1){
                System.out.println("Enter Username : ");
                String uname = sc.next();

                System.out.println("Enter Password : ");
                String pwd = sc.next();
                boolean quit = false;
                while (!quit){
                    try{

                        while (flag){
                            boolean admin = dao.adminLogin(uname,pwd);
                            if (admin == false){
                                System.out.println(admin);
                                flag = false;
                                quit = true;
                            }
                        }
                    }catch (AdminException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }



    }


}
