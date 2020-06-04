package com.solvd.logistic_company.storage;

import com.solvd.logistic_company.entity.User;

public class LocalStorage {
    private static User authUser;

    public static User getAuthUser() {
        return authUser;
    }

    public static void setAuthUser(User authUser) {
        LocalStorage.authUser = authUser;
    }
}
