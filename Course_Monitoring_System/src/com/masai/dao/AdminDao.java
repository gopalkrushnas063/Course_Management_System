package com.masai.dao;

import com.masai.exception.*;
import com.masai.model.*;

import java.security.PublicKey;
import java.util.List;

public interface AdminDao {
    //Course Update , View & Create Section
    public boolean adminLogin(String username, String password) throws AdminException;
    public String addCourse(Course course) throws CourseException;
    public String updateCourse(int cid) throws CourseException;
    public List<Course> viewCourse() throws CourseException;
    public String updateBatch(int batchid)throws BatchException;


    //Batch Update , View & Create Section
    public String addBatch(Batch batch) throws BatchException;
    public List<Batch> viewBatch()throws BatchException;

    //Faculty Update , View & Create Section
    public  String createFacultyID(Faculty faculty) throws FacultyException;
    public String updateFaculty(int fid) throws FacultyException;
    public List<Faculty> viewAllFacultyDetails() throws FacultyException;

    //•	Create, Update, View Course plan.
    public String createCoursePlan(CoursePlan coursePlan) throws CoursePlanException;
    public String updateCoursePlan(int planId) throws CoursePlanException;
}

