package com.solvd.logistic_company;

import com.solvd.logistic_company.entity.Road;
import com.solvd.logistic_company.entity.User;
import com.solvd.logistic_company.service.UserService;
import org.apache.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = Logger.getLogger(Executor.class);

    public static void main(String[] args) {
        testGetUserByUserName("Denis");
    }

    public static void testGetUserByUserName(String userName) {
        UserService userService = new UserService();
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            LOGGER.info("-------------------------------");
            LOGGER.info("There isn't any users by the username");
            LOGGER.info("-------------------------------");
        } else {
            LOGGER.info("-------------------------------");
            LOGGER.debug("Hello, " + user);
            LOGGER.info("-------------------------------");
        }
    }

    private void floydWarshall(Road[][] roadsMatrix) {
        int dist[][] = new int[4][4];
        int i, j, k;

        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++)
                dist[i][j] = roadsMatrix[i][j].getDistance();

        for (k = 0; k < 4; k++) {
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        printSolution(dist);
    }

    void printSolution(int dist[][]) {
        for (int i=0; i<4; ++i) {
            for (int j=0; j<4; ++j) {
                System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }
}
