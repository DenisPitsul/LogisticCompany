package com.solvd.logistic_company.menu;

import com.solvd.logistic_company.json.DeliveryProcessing;

import org.apache.log4j.Logger;


import java.util.Scanner;

public class DeliveryParsingMenu {

    private static final Logger LOGGER = Logger.getLogger(DeliveryParsingMenu.class);


    private Scanner in;

    private DeliveryProcessing processing;

    private void inputParsingOperation(String path) {

        processing = new DeliveryProcessing();
        processing.dataChecks(path);

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

