package com.solvd.logistic_company.entity;

public class User {
    private Long id;
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                '}';
    }
}
