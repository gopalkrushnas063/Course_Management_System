package com.masai.dao;

import com.masai.exception.AdminException;
import com.masai.model.Admin;

public interface AdminDao {
    public boolean adminLogin(String username, String password) throws AdminException;

}
