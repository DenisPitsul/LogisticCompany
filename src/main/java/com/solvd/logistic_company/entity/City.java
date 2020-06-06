package com.solvd.logistic_company.entity;

import com.solvd.logistic_company.helper.Copyable;

public class City implements Copyable {
    private Long id;
    private String name;
    private Integer storageCapacity;

    public City(String name, Integer storageCapacity) {
        this.name = name;
        this.storageCapacity = storageCapacity;
    }

    public City() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(Integer storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storageCapacity=" + storageCapacity +
                '}';
    }

    @Override
    public City copy() {
        return new City(name, storageCapacity);
    }
}
