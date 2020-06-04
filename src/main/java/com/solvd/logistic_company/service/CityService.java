package com.solvd.logistic_company.service;

import com.solvd.logistic_company.dao.ICityDAO;
import com.solvd.logistic_company.dao.impl.CityDAO;
import com.solvd.logistic_company.entity.City;

public class CityService {
    private ICityDAO cityDAO = new CityDAO();

    public City getCityById(Long id) {
        return cityDAO.getCityById(id);
    }

    public City getCityByName(String name) {
        return cityDAO.getCityByName(name);
    }

    public void updateCity(City city) {
        cityDAO.updateCity(city);
    }
}
