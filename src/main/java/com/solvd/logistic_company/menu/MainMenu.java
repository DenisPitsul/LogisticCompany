package com.solvd.logistic_company.menu;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class MainMenu {

    private static final Logger LOGGER = Logger.getLogger(MainMenu.class);

    public Scanner in;

    public void menu(){
        try {
            System.out.println("Order delivery");
            UserMenu userMenu = new UserMenu();

            System.out.println("Whant do you want to do? 1 - order delivery; 2 - exit: ");
            in = new Scanner(System.in);
            int choice = in.nextInt();
            do {
                switch (choice){
                    case 1:
                        userMenu.openMainMenu();
                        System.out.println("Do you want to change user? 1 - yes; 2 - no:");
                        int userChoice = in.nextInt();
                        while(userChoice == 1){
                            userMenu.openMainMenu();
                        }
                        break;
                    case 2:
                        throw new InterruptedException();
                    default:
                        break;
                }
                System.out.println("Whant do you want to do? 1 - order delivery; 2 - exit: ");
                choice = in.nextInt();
            } while (choice == 1);
        } catch (InterruptedException e){
            LOGGER.error(e.getMessage());
        }
        }

}
