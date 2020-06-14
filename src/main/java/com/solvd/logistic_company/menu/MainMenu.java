package com.solvd.logistic_company.menu;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private static final Logger LOGGER = Logger.getLogger(MainMenu.class);

    public Scanner in;

    public void menu() {
        try {
            UserMenu userMenu = new UserMenu();
            System.out.println("What do you want to do? 1 - order delivery; 2 - exit: ");
            in = new Scanner(System.in);
            int choice = in.nextInt();
            do {
                userMenu.openMainMenu();
                System.out.println("What do you want to do? 1 - order delivery; 2 - exit: ");
                choice = in.nextInt();
            } while (choice == 1);
            if (choice == 2){
                LOGGER.info("System exit");
            }
/*            do {
                switch (choice){
                    case 1:

                        break;
                    case 2:

                        break;
                    default:
                        LOGGER.info("Incorrect data");
                        break;
                }

            } while (choice == 1 );*/
        } catch (InputMismatchException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
