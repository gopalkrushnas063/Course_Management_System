package com.masai.dao;

import com.masai.exception.CoursePlanException;
import com.masai.exception.FacultyException;
import com.masai.model.CoursePlan;
import com.masai.model.FacultyCourseView;

import java.util.List;

public interface FacultyDao {
    public boolean facultyLogin(String username , String password) throws FacultyException;
    public List<FacultyCourseView> viewCoursePlan(int fid) throws CoursePlanException;
    public String dayWisePlan() throws FacultyException;
}
