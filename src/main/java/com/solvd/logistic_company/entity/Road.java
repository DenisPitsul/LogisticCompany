package com.solvd.logistic_company.entity;

import com.solvd.logistic_company.helper.Copyable;

public class Road implements Copyable {
    private Long id;
    private City cityFrom;
    private City cityTo;
    private Integer distance;

    public Road(City cityFrom, City cityTo, Integer distance) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
    }

    public Road() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Road{" +
                "id=" + id +
                ", cityFrom=" + cityFrom +
                ", cityTo=" + cityTo +
                ", distance=" + distance +
                '}';
    }

    @Override
    public Road copy() {
        return new Road(cityFrom.copy(), cityTo.copy(), distance);
    }
}
