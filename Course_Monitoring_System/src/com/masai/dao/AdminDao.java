package com.masai.dao;

import com.masai.exception.AdminException;
import com.masai.exception.BatchException;
import com.masai.exception.CourseException;
import com.masai.exception.FacultyException;
import com.masai.model.Admin;
import com.masai.model.Batch;
import com.masai.model.Course;
import com.masai.model.Faculty;

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


    //Faculty Update , View & Create Section
    public  String createFacultyID(Faculty faculty) throws FacultyException;
}

