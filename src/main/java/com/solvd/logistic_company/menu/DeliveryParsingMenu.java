package com.solvd.logistic_company.menu;

import com.solvd.logistic_company.algorithm.Algorithm;
import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.entity.Delivery;
import com.solvd.logistic_company.exception.IncorrectJsonPath;
import com.solvd.logistic_company.json.DeliveryRequest;
import com.solvd.logistic_company.service.CityService;
import com.solvd.logistic_company.service.DeliveryService;
import org.apache.log4j.Logger;

import java.nio.file.Paths;
import java.util.Scanner;

public class DeliveryParsingMenu {

    private static final Logger LOGGER = Logger.getLogger(DeliveryParsingMenu.class);

    private CityService cityService = new CityService();

    private Scanner in;

    private void inputParsingOperation(String path) {
        try {
            DeliveryRequest request = DeliveryRequest.fromJsonFile(Paths.get(path).toFile());
            if (request != null) {
                // check if the destination city exists
                City destinationCity = cityService.getCityByName(request.getCityTo());
                if (destinationCity == null) {
                    System.out.println("Unknown destination city " + request.getCityTo() + "!");
                    LOGGER.warn("Unknown destination city " + request.getCityTo() + "!");
                }
                // check if the source city exists
                City departureCity = cityService.getCityByName(request.getCityFrom());
                if (departureCity == null) {
                    System.out.println("Unknown departure city " + request.getCityFrom() + "!");
                    LOGGER.warn("Unknown departure city " + request.getCityFrom() + "!");
                } else {
                    Algorithm.findShortestRoads(request.getCityFrom(), request.getCityTo());
                }
                int choice;
                //   check if there's enough storageCapacity in a destination City
                if (destinationCity.getStorageCapacity() >= request.getCargo()) {
                    System.out.println("The cargo can be stored in " + request.getCityTo());
                    LOGGER.info("The cargo can be stored in " + request.getCityTo());
                    System.out.println("Do you want to store cargo in " + request.getCityTo());
                    System.out.println("1 - yes; other - exit: ");
                    choice = in.nextInt();
                    if (choice == 1) {
                        int cargo = request.getCargo();
                        DeliveryService deliveryService = new DeliveryService();
                        Delivery delivery = new Delivery(departureCity, destinationCity, cargo);
                        deliveryService.addDelivery(delivery);

                        int newStorageCapacity = destinationCity.getStorageCapacity() - delivery.getCargo();
                        destinationCity.setStorageCapacity(newStorageCapacity);
                        cityService.updateCity(destinationCity);
                        LOGGER.info("DB table updated");
                        LOGGER.info("Cargo stored in: " + request.getCityTo());
                        System.out.println("Cargo stored in: " + request.getCityTo());
                    }
                } else {
                    System.out.println("There is not enough storage capacity in " + request.getCityTo());
                    LOGGER.warn("There is not enough storage capacity in " + request.getCityTo());
                    System.out.println("Do you want to calculate a route to the nearest city?");
                    System.out.println("1 - yes; other - exit: ");
                    choice = in.nextInt();
                    if (choice == 1) {
                        City nearestCity = Algorithm.findNearestCity(request.getCityFrom(), request.getCargo());
                        if (nearestCity == null) {
                            System.out.println("There is no reachable cities with capacity over " + request.getCargo());
                        } else {
                            System.out.println("The cargo can be stored in " + nearestCity.getName() +
                                    " (capacity: " + nearestCity.getStorageCapacity() + ")");

                            System.out.println("Do you want to store cargo in " + nearestCity.getName());
                            System.out.println("1 - yes; other - exit: ");
                            choice = in.nextInt();
                            if (choice == 1) {
                                int cargo = request.getCargo();
                                DeliveryService deliveryService = new DeliveryService();
                                Delivery delivery = new Delivery(departureCity, nearestCity, cargo);
                                deliveryService.addDelivery(delivery);

                                int newStorageCapacity = nearestCity.getStorageCapacity() - delivery.getCargo();
                                nearestCity.setStorageCapacity(newStorageCapacity);
                                cityService.updateCity(nearestCity);
                                LOGGER.info("DB table updated");
                                LOGGER.info("Cargo stored in: " + nearestCity.getName());
                                System.out.println("Cargo stored in: " + nearestCity.getName());
                            }
                        }
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                throw new IncorrectJsonPath();
            }
        } catch (IncorrectJsonPath e) {
            System.out.println(e.getMessage());
            LOGGER.error(e);
        }
    }

    public void deliveryMenu() {
        in = new Scanner(System.in);
        System.out.println("Do you want to add new delivery? 1 - yes; 2 - no:");
        int choice = in.nextInt();
        while (choice == 1) {
            in = new Scanner(System.in);
            System.out.println("Enter path to the JSON file:");
            String path = in.nextLine();
            inputParsingOperation(path);
            System.out.println("Do you want to add new delivery? 1 - yes; 2 - no:");
            choice = in.nextInt();
        }
    }
}

