package com.solvd.logistic_company.json;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.logistic_company.entity.Delivery;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class DeliveryRequest {
    private static final Logger LOGGER = Logger.getLogger(DeliveryRequest.class);

    @JsonAlias("city_from")
    private String cityFrom;
    @JsonAlias("city_to")
    private String cityTo;
    private int cargo;

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    @Override
    public String toString() {
        return "DeliveryRequest{" +
                "cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", cargo=" + cargo +
                '}';
    }

    public static DeliveryRequest fromJsonFile(File file) {
        LOGGER.debug("Loading delivery information from " + file.getAbsolutePath());
        DeliveryRequest data = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(file, DeliveryRequest.class);

        } catch (IOException e) {
            LOGGER.error("Error in loadDeliveryFromFile:", e);
        }
        return data;
    }

}