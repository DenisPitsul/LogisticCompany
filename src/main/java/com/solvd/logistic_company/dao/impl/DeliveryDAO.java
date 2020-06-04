package com.solvd.logistic_company.dao.impl;

import com.solvd.logistic_company.dao.IDeliveryDAO;
import com.solvd.logistic_company.entity.Delivery;
import com.solvd.logistic_company.helper.CityNames;
import com.solvd.logistic_company.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class DeliveryDAO implements IDeliveryDAO {
    private static final Logger LOGGER = Logger.getLogger(DeliveryDAO.class);

    private IDeliveryDAO deliveryDAO;
    private Class<IDeliveryDAO> deliveryDAOClass = IDeliveryDAO.class;

    @Override
    public List<Delivery> getAllDeliveries() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        deliveryDAO = session.getMapper(deliveryDAOClass);
        List<Delivery> deliveryList = deliveryDAO.getAllDeliveries();
        LOGGER.info("Got all deliveries");
        session.close();

        return null;
    }

    @Override
    public List<Delivery> getDeliveriesByCityNames(CityNames cityNames) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        deliveryDAO = session.getMapper(deliveryDAOClass);
        List<Delivery> deliveryList = deliveryDAO.getDeliveriesByCityNames(cityNames);
        LOGGER.info("Got deliveries by city names");
        session.close();

        return deliveryList;
    }

    @Override
    public void addDelivery(Delivery delivery) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        deliveryDAO = session.getMapper(deliveryDAOClass);
        deliveryDAO.addDelivery(delivery);
        LOGGER.info("Added new delivery");
        session.commit();
        session.close();
    }
}
