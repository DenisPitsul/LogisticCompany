package com.solvd.logistic_company.menu;

import com.solvd.logistic_company.algorithm.Algorithm;
import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.exception.IncorrectJsonPath;
import com.solvd.logistic_company.json.DeliveryRequest;
import com.solvd.logistic_company.service.CityService;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DeliveryParsingMenu {

    private static final Logger LOGGER = Logger.getLogger(DeliveryParsingMenu.class);

    private CityService cityService = new CityService();

    private Scanner in;

    public void inputParsingOperation(String path) {
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

                //   check if there's enough storageCapacity in a destination City
                if (destinationCity.getStorageCapacity() >= request.getCargo()) {
                    System.out.println("The cargo can be stored in " + request.getCityTo());
                    LOGGER.info("The cargo can be stored in " + request.getCityTo());
                } else {
                    System.out.println("There is not enough storage capacity in " + request.getCityTo());
                    LOGGER.warn("here is not enough storage capacity in " + request.getCityTo());
                    System.out.println("Do you want to calculate a route to the nearest city?");
                    System.out.println("1 - yes; 2 - exit: ");
                    int choice = in.nextInt();
                    if (choice == 1) {
                        // calc nearest city and add it to alg;
                        System.out.println("This feature doesn't exist"); //This option will be replaced by calculation
                    } else {
                        System.exit(0);
                    }

                    // TODO: calculate the nearest city with free capacity
                }
            } else {
                throw new IncorrectJsonPath();
            }
        } catch (IncorrectJsonPath e){
            LOGGER.error(e.getMessage());
        }
    }

    public void deliveryMenu(){
        in = new Scanner(System.in);
        System.out.println("Enter path to the JSON file:");
        String path = in.nextLine();
        inputParsingOperation(path);
    }
}
