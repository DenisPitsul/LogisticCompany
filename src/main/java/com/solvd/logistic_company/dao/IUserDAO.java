package com.solvd.logistic_company.dao;

import com.solvd.logistic_company.entity.User;

public interface IUserDAO {

    User getUserByUserName(String userName);
}
