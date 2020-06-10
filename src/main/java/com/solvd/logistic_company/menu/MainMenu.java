package com.solvd.logistic_company.menu;

import java.util.Scanner;

public class MainMenu {

    public Scanner in;

    public void menu(){
        System.out.println("Order delivery");
        UserMenu userMenu = new UserMenu();

        System.out.println("Whant do you want to do? 1 - order delivery; 2 - exit: ");
        in = new Scanner(System.in);
        int choice = in.nextInt();
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
                System.exit(0);
                break;
            default:
                break;
        }
    }

}
