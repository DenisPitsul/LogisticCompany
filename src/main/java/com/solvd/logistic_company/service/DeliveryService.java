package com.solvd.logistic_company.service;

import com.solvd.logistic_company.dao.IDeliveryDAO;
import com.solvd.logistic_company.dao.impl.DeliveryDAO;
import com.solvd.logistic_company.entity.Delivery;
import com.solvd.logistic_company.helper.CityNames;

import java.util.List;

public class DeliveryService {
    private IDeliveryDAO deliveryDAO = new DeliveryDAO();

    public List<Delivery> getAllDeliveries() {
        return deliveryDAO.getAllDeliveries();
    }

    public List<Delivery> getDeliveriesByCityNames(CityNames cityNames) {
        return deliveryDAO.getDeliveriesByCityNames(cityNames);
    }

    public void addDelivery(Delivery delivery) {
        deliveryDAO.addDelivery(delivery);
    }
}
