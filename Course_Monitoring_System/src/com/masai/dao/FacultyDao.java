package com.masai.dao;

import com.masai.exception.FacultyException;

public interface FacultyDao {
    public boolean facultyLogin(String username , String password) throws FacultyException;
}
