package com.solvd.logistic_company.menu;

import org.apache.log4j.Logger;

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
            while (choice == 1) {
                userMenu.openMainMenu();
                System.out.println("What do you want to do? 1 - order delivery; 2 - exit: ");
                choice = in.nextInt();
            }
        } catch (InputMismatchException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
