package com.solvd.logistic_company.dao.impl;

import com.solvd.logistic_company.dao.IUserDAO;
import com.solvd.logistic_company.entity.User;
import com.solvd.logistic_company.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class UserDAO implements IUserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    private IUserDAO userDAO;
    private Class<IUserDAO> userDAOClass = IUserDAO.class;

    @Override
    public User getUserByUserName(String userName) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        userDAO = session.getMapper(userDAOClass);
        User user = userDAO.getUserByUserName(userName);
        LOGGER.info(user == null ? "There isn't any user" : "Got user");
        session.close();

        return user;
    }
}
