package com.solvd.logistic_company.dao;

import com.solvd.logistic_company.entity.City;

public interface ICityDAO {

    City getCityById(Long id);

    City getCityByName(String name);

    void updateCity(City city);
}
