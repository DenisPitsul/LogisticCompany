package com.solvd.logistic_company.service;

import com.solvd.logistic_company.dao.IUserDAO;
import com.solvd.logistic_company.dao.UserDAO;
import com.solvd.logistic_company.entity.User;

public class UserService {
    private IUserDAO userDAO = new UserDAO();

    public User getUserByUserName(String userName) {
        return userDAO.getUserByUserName(userName);
    }
}
