package com.solvd.logistic_company.algorithm;

import com.solvd.logistic_company.entity.City;
import com.solvd.logistic_company.entity.Road;
import com.solvd.logistic_company.service.CityService;
import com.solvd.logistic_company.service.RoadService;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    public static void findShortestRoads(String cityFrom, String cityTo) {
        CityService cityService = new CityService();
        List<City> cityList = cityService.getAllCities();

        RoadService roadService = new RoadService();
        List<Road> roadList = roadService.getAllRoads();

        Road[][] roadMatrix = getRoadMatrix(cityList, roadList);
        initNullRoadElements(roadMatrix, cityList);
        System.out.println("Roads from database: ");
        outputRoadMatrix(roadMatrix);
        calculateTotalDistances(roadMatrix);

        System.out.println("Shortest roads: ");
        outputRoadMatrix(roadMatrix);

        Road shortestRoad = findRoadByCityNames(roadMatrix, cityFrom, cityTo);
        if (shortestRoad == null || shortestRoad.getDistance() < 0) {
            System.out.println("There are no roads from " + cityFrom + " to " + cityTo);
        } else {
            System.out.println("Shortest road: " + shortestRoad);
        }
    }

    private static void calculateTotalDistances(Road[][] roadMatrix) {
        boolean isFinished = false;
        while (!isFinished) {
            Road[][] compareRoadMatrix = getCopyRoadMatrix(roadMatrix);

            for (int i = 0; i < roadMatrix.length; i++) {
                for (int j = 0; j < roadMatrix[i].length; j++) {
                    if (roadMatrix[i][j].getDistance() > 0) {
                        for (int k = 0; k < roadMatrix[j].length; k++) {
                            if (roadMatrix[i][k].getDistance() > roadMatrix[i][j].getDistance() + roadMatrix[j][k].getDistance())
                                roadMatrix[i][k].setDistance(roadMatrix[i][j].getDistance() + roadMatrix[j][k].getDistance());
                        }
                    }
                }
            }

            boolean isMatrixEquals = true;
            outerloop:
            for (int i = 0; i < roadMatrix.length; i++) {
                for (int j = 0; j < roadMatrix[i].length; j++) {
                    if (!roadMatrix[i][j].getDistance().equals(compareRoadMatrix[i][j].getDistance())) {
                        isMatrixEquals = false;
                        break outerloop;
                    }
                }
            }
            if (isMatrixEquals)
                isFinished = true;
        }
    }

    public static Road[][] getRoadMatrix(List<City> cityList, List<Road> roadList) {
        Road[][] roadMatrix = new Road[cityList.size()][cityList.size()];
        for (int i = 0; i < cityList.size(); i++) {
            for (int j = 0; j < cityList.size(); j++) {
                for (int k = 0; k < roadList.size(); k++) {
                    if (cityList.get(i).getId().equals(roadList.get(k).getCityFrom().getId())
                            && cityList.get(j).getId().equals(roadList.get(k).getCityTo().getId())) {
                        roadMatrix[i][j] = roadList.get(k);
                        //  roadMatrix[j][i] = roadList.get(k); // roads are two way
                    }
                }
            }
        }
        return roadMatrix;
    }

    public static void initNullRoadElements(Road[][] roadMatrix, List<City> cityList) {
        for (int i = 0; i < roadMatrix.length; i++) {
            for (int j = 0; j < roadMatrix[i].length; j++) {
                if (i == j) {
                    roadMatrix[i][j] = new Road(cityList.get(i), cityList.get(j), 0);
                } else {
                    if (roadMatrix[i][j] == null) {
                        roadMatrix[i][j] = new Road(cityList.get(i), cityList.get(j), -1);
                    }
                }
            }
        }
    }

    public static Road[][] getCopyRoadMatrix(Road[][] roadMatrix) {
        Road[][] copyRoadMatrix = new Road[roadMatrix.length][roadMatrix[0].length];
        for (int i = 0; i < roadMatrix.length; i++) {
            for (int j = 0; j < roadMatrix[i].length; j++) {
                copyRoadMatrix[i][j] = roadMatrix[i][j].copy();
            }
        }
        return copyRoadMatrix;
    }

    public static void outputRoadMatrix(Road[][] roadMatrix) {
        for (int i = 0; i < roadMatrix.length; i++) {
            for (int j = 0; j < roadMatrix[i].length; j++) {
                System.out.print(roadMatrix[i][j].getDistance() + "\t");
            }
            System.out.println();
        }
    }

    public static Road findRoadByCityNames(Road[][] roadMatrix, String cityFrom, String cityTo) {
        Road shortestRoad = null;
        for (int i = 0; i < roadMatrix.length; i++) {
            for (int j = 0; j < roadMatrix[i].length; j++) {
                if (roadMatrix[i][j].getCityFrom().getName().equals(cityFrom)
                        && roadMatrix[i][j].getCityTo().getName().equals(cityTo)) {
                    shortestRoad = roadMatrix[i][j];
                }
            }
        }
        return shortestRoad;
    }

    private static List<Road> findRoadsByCityFrom(Road[][] roadMatrix, String cityFrom) {
        List<Road> roadList = new ArrayList<>();
        for (int i = 0; i < roadMatrix.length; i++) {
            for (int j = 0; j < roadMatrix[i].length; j++) {
                if (roadMatrix[i][j].getCityFrom().getName().equals(cityFrom)) {
                    roadList.add(roadMatrix[i][j]);
                }
            }
        }
        return roadList;
    }

    private static City findNearestCityTo(List<Road> roadList) {
        City nearestCity;
        Road min = null;
        for (int i = 0; i < roadList.size(); i++) {
            if (roadList.get(i).getDistance() > 0) {
                min = roadList.get(i);
                break;
            }
        }
        for (int i = 0; i < roadList.size(); i++) {
            if (roadList.get(i).getDistance() > 0)
                if (min.getDistance() > roadList.get(i).getDistance())
                    min = roadList.get(i);
        }
        if (min == null) {
            return null;
        }
        nearestCity = min.getCityTo();
        return nearestCity;
    }

    public static City findNearestCity(String cityFrom, int minCapacity) {
        CityService cityService = new CityService();
        List<City> allCities = cityService.getAllCities();

        List<City> cityList = new ArrayList<>();
        for (City city :
                allCities) {
            if (city.getName().equals(cityFrom) || city.getStorageCapacity() >= minCapacity) {
                cityList.add(city);
            }
        }

        RoadService roadService = new RoadService();
        List<Road> roadList = roadService.getAllRoads();

        Road[][] roadMatrix = getRoadMatrix(cityList, roadList);
        initNullRoadElements(roadMatrix, cityList);
        calculateTotalDistances(roadMatrix);

        List<Road> roadsFromStartingCity = findRoadsByCityFrom(roadMatrix, cityFrom);

        return findNearestCityTo(roadsFromStartingCity);
    }
}


