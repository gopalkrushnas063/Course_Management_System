package com.masai.dao;

import com.masai.exception.CoursePlanException;
import com.masai.exception.FacultyException;
import com.masai.model.CoursePlan;

import java.util.List;

public interface FacultyDao {
    public boolean facultyLogin(String username , String password) throws FacultyException;
    public List<CoursePlan> viewCoursePlan() throws CoursePlanException;
}
