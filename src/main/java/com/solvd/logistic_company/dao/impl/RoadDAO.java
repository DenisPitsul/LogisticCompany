package com.solvd.logistic_company.dao.impl;

import com.solvd.logistic_company.dao.IRoadDAO;
import com.solvd.logistic_company.entity.Road;
import com.solvd.logistic_company.helper.CityNames;
import com.solvd.logistic_company.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class RoadDAO implements IRoadDAO {
    private static final Logger LOGGER = Logger.getLogger(RoadDAO.class);

    private IRoadDAO roadDAO;
    private Class<IRoadDAO> roadDAOClass = IRoadDAO.class;

    @Override
    public List<Road> getAllRoads() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        roadDAO = session.getMapper(roadDAOClass);
        List<Road> roadList = roadDAO.getAllRoads();
        LOGGER.info("Got all roads");
        session.close();

        return roadList;
    }

    @Override
    public List<Road> getRoadsByCityNames(CityNames cityNames) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        roadDAO = session.getMapper(roadDAOClass);
        List<Road> roadList = roadDAO.getRoadsByCityNames(cityNames);
        LOGGER.info("Got roads by city names");
        session.close();

        return roadList;
    }
}
