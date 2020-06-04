package com.solvd.logistic_company.menu;

import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.json.DeliveryRequest;
import com.solvd.logistic_company.service.CityService;
import org.apache.log4j.Logger;

import java.nio.file.Paths;
import java.util.Scanner;

public class DeliveryParsingMenu {

    private static final Logger LOGGER = Logger.getLogger(UserMenu.class);

    private CityService cityService = new CityService();

    private Scanner in;

    public void inputParsingOperation(){
        //to do - write parsing delivery operation
        in = new Scanner(System.in);
        System.out.println("Enter path to the JSON file:");
        String path = in.nextLine();
        DeliveryRequest request = DeliveryRequest.fromJsonFile(Paths.get(path).toFile());
        if (request == null) {
            System.out.println("Invalid file path or invalid JSON!");
            return;
        }
        // check if the destination city exists
        City destinationCity = cityService.getCityByName(request.getCityTo());
        if (destinationCity == null){
            System.out.println("Unknown destination city " + request.getCityTo() + "!");
            LOGGER.warn("Unknown destination city " + request.getCityTo() + "!");
            return;
        }
        // check if the source city exists
        City departureCity = cityService.getCityByName(request.getCityFrom());
        if (departureCity == null) {
            System.out.println("Unknown departure city " + request.getCityFrom() + "!");
            LOGGER.warn("Unknown departure city " + request.getCityFrom()+ "!");
        }
        }

        // TODO: calculate the nearest city with free capacity

}
