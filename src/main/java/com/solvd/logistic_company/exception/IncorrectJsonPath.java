package com.solvd.logistic_company.exception;

public class IncorrectJsonPath extends Exception {

    private static final String INCORRECT_PATH = "Incorrect path to file! Please try again";
    //private static final String INCORRECT_CITY_NAME = "Incorrect city name! Please try again";

    public IncorrectJsonPath(){ super(INCORRECT_PATH); }
}
