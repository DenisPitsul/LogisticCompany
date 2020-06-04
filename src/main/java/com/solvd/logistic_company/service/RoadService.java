package com.solvd.logistic_company.service;

import com.solvd.logistic_company.dao.IRoadDAO;
import com.solvd.logistic_company.dao.impl.RoadDAO;
import com.solvd.logistic_company.entity.Road;
import com.solvd.logistic_company.helper.CityNames;

import java.util.List;

public class RoadService {
    private IRoadDAO roadDAO = new RoadDAO();

    public List<Road> getAllRoads() {
        return roadDAO.getAllRoads();
    }

    public List<Road> getRoadsByCityNames(CityNames cityNames) {
        return roadDAO.getRoadsByCityNames(cityNames);
    }
}
