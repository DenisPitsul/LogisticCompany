package com.solvd.logistic_company.entity;

public class Delivery {
    private Long id;
    private City cityFrom;
    private City cityTo;
    private Integer cargo;

    public Delivery(City cityFrom, City cityTo, Integer cargo) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.cargo = cargo;
    }

    public Delivery() { }

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

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", cityFrom=" + cityFrom +
                ", cityTo=" + cityTo +
                ", cargo=" + cargo +
                '}';
    }
}
