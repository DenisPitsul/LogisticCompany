package com.solvd.logistic_company.json;

import com.solvd.logistic_company.algorithm.Algorithm;
import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.entity.Delivery;
import com.solvd.logistic_company.exception.IncorrectJsonPath;
import com.solvd.logistic_company.service.CityService;
import com.solvd.logistic_company.service.DeliveryService;
import org.apache.log4j.Logger;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeliveryProcessing {

    private static final Logger LOGGER = Logger.getLogger(DeliveryProcessing.class);

    private CityService cityService = new CityService();

    private Scanner in;

    /**
     * The method checks possibility of cargo transportation between cities according to Json request.
     *
     * @author Olga Opryshko
     * @author Serg Kruchinskii
     */
    public void dataChecks(String path) {

        in = new Scanner(System.in);

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

                    cargoToCity(request, departureCity, destinationCity, request.getCityTo());

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

                            cargoToCity(request, departureCity, nearestCity, nearestCity.getName());
                        }
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                throw new IncorrectJsonPath();
            }
        } catch (IncorrectJsonPath | SQLException | InputMismatchException e) {
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * The method writes the history of orders into the database's table "Delivery" and
     * updates storage capacity in a table "City".
     *
     * @author Serg Kruchinskii
     * @author Olga Opryshko
     */
    private void cargoToCity(DeliveryRequest request, City departureCity, City newCity, String name) {
        int choice;
        System.out.println("Do you want to store cargo in " + name);
        System.out.println("1 - yes; other - exit: ");
        choice = in.nextInt();
        if (choice == 1) {
            int cargo = request.getCargo();
            DeliveryService deliveryService = new DeliveryService();
            Delivery delivery = new Delivery(departureCity, newCity, cargo);
            deliveryService.addDelivery(delivery);

            int newStorageCapacity = newCity.getStorageCapacity() - delivery.getCargo();
            newCity.setStorageCapacity(newStorageCapacity);

            int newDepartureCapacity = departureCity.getStorageCapacity() + delivery.getCargo();
            departureCity.setStorageCapacity(newDepartureCapacity);
            cityService.updateCity(departureCity);

            cityService.updateCity(newCity);
            LOGGER.info("DB table updated");
            LOGGER.info("Cargo stored in: " + name);
            System.out.println("Cargo stored in: " + name);
        }
    }

}
