package com.solvd.logistic_company.service;

import com.solvd.logistic_company.dao.ICityDAO;
import com.solvd.logistic_company.dao.impl.CityDAO;
import com.solvd.logistic_company.entity.City;

import java.sql.SQLException;
import java.util.List;

public class CityService {
    private ICityDAO cityDAO = new CityDAO();

    public City getCityById(Long id) {
        return cityDAO.getCityById(id);
    }

    public City getCityByName(String name) throws SQLException {
        return cityDAO.getCityByName(name);
    }

    public void updateCity(City city) {
        cityDAO.updateCity(city);
    }

    public List<City> getAllCities() { return cityDAO.getAllCities(); }
}
