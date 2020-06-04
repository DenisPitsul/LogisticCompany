package com.solvd.logistic_company.dao;

import com.solvd.logistic_company.entity.Road;
import com.solvd.logistic_company.helper.CityNames;

import java.util.List;

public interface IRoadDAO {

    List<Road> getAllRoads();

    List<Road> getRoadsByCityNames(CityNames cityNames);
}
