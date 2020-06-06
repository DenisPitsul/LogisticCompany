package com.solvd.logistic_company.algorithm;

import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.entity.Road;
import com.solvd.logistic_company.service.CityService;
import com.solvd.logistic_company.service.RoadService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Algorithm {

    private final  static Logger LOGGER = Logger.getLogger(Algorithm.class);

    public static void main(String[] args) {
        algorithm();
    }

    public static void algorithm(){

        int n = 5;

        CityService cityService = new CityService();
        LOGGER.info(cityService.getAllCities());
        List<City> listCity = cityService.getAllCities();

        RoadService roadService = new RoadService();
        LOGGER.info(roadService.getAllRoads());
        List<Road> listRoad = roadService.getAllRoads();

        Road [][] roadMatrix = new Road[listCity.size()][listCity.size()];

        for (int i = 0; i < listCity.size(); i++){
            for (int j = 0; j < listCity.size(); j++){
                for (int k = 0; k < listRoad.size(); k++){
                    if (listCity.get(i).getId().equals(listRoad.get(k).getCityFrom().getId())
                            && listCity.get(j).getId().equals(listRoad.get(k).getCityTo().getId())){
                        roadMatrix[i][j] = listRoad.get(k);

                    }
                }
            }
        }

        for (int i = 0; i < roadMatrix.length; i++){
            for (int j = 0; j < roadMatrix[i].length; j++){
                if (i==j){
                    roadMatrix[i][j] = new Road(listCity.get(i), listCity.get(j), 0);
                } else {
                    if (roadMatrix[i][j] == null) {
                        roadMatrix[i][j] = new Road(listCity.get(i), listCity.get(j), -1);
                    }
                }
                System.out.print(roadMatrix[i][j].getDistance() + "\t");
            }
            System.out.println();
        }

        for (int i = 0; i < roadMatrix.length; i++){
            for (int j = 0; j < roadMatrix[i].length; j++){
                if (roadMatrix[i][j].getDistance() > 0){
                    for (int k = 0; k < roadMatrix[j].length; k++){
                        //if (roadMatrix[i][k].getDistance() > 0 && roadMatrix[j][k].getDistance() > 0){
                            if (roadMatrix[i][k].getDistance() > roadMatrix[i][j].getDistance() + roadMatrix[j][k].getDistance()) {
                                roadMatrix[i][k].setDistance(roadMatrix[i][j].getDistance() + roadMatrix[j][k].getDistance());
                            }

                        //}
                    }
                }
            }
        }
        System.out.println();

        for (int i = 0; i < roadMatrix.length; i++){
            for (int j = 0; j < roadMatrix[i].length; j++){
                System.out.print(roadMatrix[i][j].getDistance() + "\t");
            }
            System.out.println();
        }

/*
        int [][] matrixWeight = {
                {25,10,25,25,25},
                {25,25,12,25,8},
                {25,25,25,8,25},
                {25,6,8,25,3},
                {25,25,25,3,25}};
        int [][] matrixHistory = {
                {0,2,0,0,0},
                {0,0,3,0,5},
                {0,0,0,4,0},
                {0,2,3,0,5},
                {0,0,0,4,0}
        };*/



/*        System.out.println("Matrix weight: ");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%3d", matrixWeight[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Matrix history: ");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%3d", matrixHistory[i][j]);
            }
            System.out.println();
        }*/

/*        Scanner scanner = new Scanner(System.in);

        System.out.println("City from: ");
        int cityFrom = scanner.nextInt();
        System.out.println("City to: ");
        int cityTo = scanner.nextInt();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (matrixWeight[i][j] > -1){
                    for (int k = 0; k < n; k++){
                        if (matrixWeight[i][k] > matrixWeight[i][j] + matrixWeight[j][k]) {
                            matrixWeight[i][k] = matrixWeight[i][j] + matrixWeight[j][k];
                            matrixHistory[i][k] = matrixHistory[i][j];
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("All shortest distance: ");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%3d", matrixWeight[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Matrix of vertices through which we found the shortest paths: ");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%3d", matrixHistory[i][j]);
            }
            System.out.println();
        }

        System.out.println("Shortest distance between city from " + cityFrom
                + " and city to " + cityTo + ": " + matrixWeight[cityFrom][cityTo]);*/

    }
}


