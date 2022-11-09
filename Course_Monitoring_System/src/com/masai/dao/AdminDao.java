package com.masai.dao;

import com.masai.exception.AdminException;
import com.masai.exception.CourseException;
import com.masai.model.Admin;
import com.masai.model.Course;

import java.security.PublicKey;
import java.util.List;

public interface AdminDao {
    public boolean adminLogin(String username, String password) throws AdminException;
    public String addCourse(Course course) throws CourseException;
    public String updateCourse(int cid) throws CourseException;
    public List<Course> viewCourse() throws CourseException;
}
