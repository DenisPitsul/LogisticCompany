package com.solvd.logistic_company.dao.impl;

import com.solvd.logistic_company.dao.ICityDAO;
import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class CityDAO implements ICityDAO {
    private static final Logger LOGGER = Logger.getLogger(CityDAO.class);

    private ICityDAO cityDAO;
    private Class<ICityDAO> cityDAOClass = ICityDAO.class;

    @Override
    public City getCityById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        cityDAO = session.getMapper(cityDAOClass);
        City city = cityDAO.getCityById(id);
        LOGGER.info("Got city by id");
        session.close();

        return city;
    }

    @Override
    public City getCityByName(String name) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        cityDAO = session.getMapper(cityDAOClass);
        City city = cityDAO.getCityByName(name);
        LOGGER.info("Got city by name");
        session.close();

        return city;
    }

    @Override
    public List<City> getAllCities() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        cityDAO = session.getMapper(cityDAOClass);
        List<City> cities = cityDAO.getAllCities();
        LOGGER.info("Got all city");
        session.close();

        return cities;
    }

    @Override
    public void updateCity(City city) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        cityDAO = session.getMapper(cityDAOClass);
        cityDAO.updateCity(city);
        LOGGER.info("Updated city");
        session.commit();
        session.close();
    }
}
