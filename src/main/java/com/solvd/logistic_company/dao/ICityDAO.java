package com.solvd.logistic_company.dao;

import com.solvd.logistic_company.entity.City;

import java.util.List;

public interface ICityDAO {

    City getCityById(Long id);

    City getCityByName(String name);

    List<City> getAllCities();

    void updateCity(City city);
}
