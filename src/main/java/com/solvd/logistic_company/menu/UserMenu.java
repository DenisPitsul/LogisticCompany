package com.solvd.logistic_company.menu;

import com.solvd.logistic_company.entity.User;
import com.solvd.logistic_company.service.UserService;
import com.solvd.logistic_company.storage.LocalStorage;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {

    private static final Logger LOGGER = Logger.getLogger(UserMenu.class);

    private UserService userService;

    private Scanner in;

    public UserMenu() {
        this.userService = new UserService();
    }

    public void openMainMenu() {
        try {
            in = new Scanner(System.in);
            System.out.println("Enter user which have to be login: ");
            String userName = in.nextLine();
            User user = getUserFromDB(userName);
            if (user != null) {
                openDeliveryParsingMenu();
                System.out.println("Do you want to add new delivery? 1 - yes; 2 - no: ");
                int choice = in.nextInt();
                switch (choice) {
                    case 1:
                        openDeliveryParsingMenu();
                        break;
                    case 2:
                        LOGGER.info("Exit");
                        break;
                    default:
                        LOGGER.info("Incorrect data!");
                        break;
                    }
                }
            } catch(InputMismatchException e){
            LOGGER.error(e.getStackTrace());
        }
    }

    private User getUserFromDB(String userName){
        User user = userService.getUserByUserName(userName);
        if (user != null) {
            LOGGER.info("-------------------------------");
            LocalStorage.setAuthUser(user);
            LOGGER.debug("Authorized by: " + user);
            LOGGER.info("-------------------------------");
        } else {
            LOGGER.info("-------------------------------");
            LOGGER.info("There isn't any users by the username");
            LOGGER.info("-------------------------------");
        }
        return user;
    }

    private void openDeliveryParsingMenu() {
        DeliveryParsingMenu deliveryParsingMenu = new DeliveryParsingMenu();
        deliveryParsingMenu.deliveryMenu();
    }
}

