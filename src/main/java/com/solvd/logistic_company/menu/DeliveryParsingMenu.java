package com.solvd.logistic_company.menu;

import com.solvd.logistic_company.algorithm.Algorithm;
import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.exception.IncorrectJsonPath;
import com.solvd.logistic_company.json.DeliveryRequest;
import com.solvd.logistic_company.service.CityService;
import org.apache.log4j.Logger;
import org.omg.CORBA.INITIALIZE;

import java.nio.file.Paths;
import java.util.Scanner;

public class DeliveryParsingMenu {

    private static final Logger LOGGER = Logger.getLogger(DeliveryParsingMenu.class);

    private CityService cityService = new CityService();

    private Scanner in;

    public void inputParsingOperation() {
        // parsing delivery operation
        in = new Scanner(System.in);
        System.out.println("Enter path to the JSON file:");
        String path = in.nextLine();
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

            //check there's enough storageCapacity in a destination City
//        if (destinationCity.getStorageCapacity() >= request.getCargo()) {
//            System.out.println("The cargo can be stored in " + request.getCityTo());
//            LOGGER.info("The cargo can be stored in " + request.getCityTo());
//        } else {
//            System.out.println("There is not enough storage capacity in " + request.getCityTo());
//            LOGGER.warn("here is not enough storage capacity in " + request.getCityTo());
//        }


            // TODO: calculate the nearest city with free capacity

        }
    }
}
