package com.solvd.logistic_company;

import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.entity.Delivery;
import com.solvd.logistic_company.entity.Road;
import com.solvd.logistic_company.entity.User;
import com.solvd.logistic_company.helper.CityNames;
import com.solvd.logistic_company.service.CityService;
import com.solvd.logistic_company.service.DeliveryService;
import com.solvd.logistic_company.service.RoadService;
import com.solvd.logistic_company.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class Executor {
    private static final Logger LOGGER = Logger.getLogger(Executor.class);

    public static void main(String[] args) {

        // testGetUserByUserName();


        // testGetAllRoads();
        // testGetRoadsByCityNames();

        // testAddDelivery();
        // testGetCityByName();
        // testGetDeliveriesByCityNames();
    }

    public static void testGetUserByUserName() {
        UserService userService = new UserService();
        User user = userService.getUserByUserName("Denis");
        if (user == null) {
            LOGGER.info("-------------------------------");
            LOGGER.info("There isn't any users by the username");
            LOGGER.info("-------------------------------");
        } else {
            LOGGER.info("-------------------------------");
            LOGGER.debug("Hello, " + user);
            LOGGER.info("-------------------------------");
        }
    }

    public static void testGetCityByName() {
        CityService cityService = new CityService();
        City city = cityService.getCityByName("Kiev");
        LOGGER.debug("City by name: " + city);
    }

    public static void testGetAllRoads() {
        RoadService roadService = new RoadService();
        List<Road> roadList = roadService.getAllRoads();
        LOGGER.debug("All roads: " + roadList);
    }

    public static void testGetRoadsByCityNames() {
        RoadService roadService = new RoadService();
        CityNames cityNames = new CityNames("Chernivtsi", "Lviv");
        List<Road> roadList = roadService.getRoadsByCityNames(cityNames);
        LOGGER.debug("Roads by city names: " + roadList);
    }

    public static void testGetDeliveriesByCityNames() {
        DeliveryService deliveryService = new DeliveryService();
        CityNames cityNames = new CityNames("Chernivtsi", "Kiev");
        List<Delivery> deliveryList = deliveryService.getDeliveriesByCityNames(cityNames);
        LOGGER.debug("Deliveries by city names: " + deliveryList);
    }

    public static void testAddDelivery() {
        CityService cityService = new CityService();
        City cityFrom = cityService.getCityByName("Chernivtsi");
        City cityTo = cityService.getCityByName("Kiev");

        DeliveryService deliveryService = new DeliveryService();
        Delivery delivery = new Delivery(cityFrom, cityTo, 250);
        if (cityTo.getStorageCapacity() >= delivery.getCargo()) {
            deliveryService.addDelivery(delivery);
            int newStorageCapacity = cityTo.getStorageCapacity() - delivery.getCargo();
            cityTo.setStorageCapacity(newStorageCapacity);
            cityService.updateCity(cityTo);
            LOGGER.debug("Added new delivery: " + delivery);
            LOGGER.debug("Added city to: " + cityTo);
        } else {
            LOGGER.info("There is no enough space on storage capacity in city " + cityTo.getName());
        }
    }

    private void floydWarshall(Road[][] roadsMatrix) {
        int dist[][] = new int[4][4];
        int i, j, k;

        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++)
                dist[i][j] = roadsMatrix[i][j].getDistance();

        for (k = 0; k < 4; k++) {
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        printSolution(dist);
    }

    void printSolution(int dist[][]) {
        for (int i=0; i<4; ++i) {
            for (int j=0; j<4; ++j) {
                System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }
}