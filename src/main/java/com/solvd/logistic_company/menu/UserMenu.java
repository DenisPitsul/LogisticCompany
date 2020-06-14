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
            System.out.println("Enter username: ");
            String userName = in.nextLine();
            User user = getUserFromDB(userName);
            if (user != null) {
                openDeliveryParsingMenu();
            }
        } catch (InputMismatchException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    private User getUserFromDB(String userName) {
        User user = userService.getUserByUserName(userName);
        if (user != null) {
            LOGGER.debug("Authorized user: " + user);
            LocalStorage.setAuthUser(user);
        } else {
            LOGGER.info("There is no users with username " + userName);
        }
        return user;
    }

    private void openDeliveryParsingMenu() {
        DeliveryParsingMenu deliveryParsingMenu = new DeliveryParsingMenu();
        deliveryParsingMenu.deliveryMenu();
    }
}

