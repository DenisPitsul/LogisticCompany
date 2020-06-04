package com.solvd.logistic_company.dao;

import com.solvd.logistic_company.entity.Delivery;
import com.solvd.logistic_company.helper.CityNames;

import java.util.List;

public interface IDeliveryDAO {

    List<Delivery> getAllDeliveries();

    List<Delivery> getDeliveriesByCityNames(CityNames cityNames);

    void addDelivery(Delivery delivery);
}
