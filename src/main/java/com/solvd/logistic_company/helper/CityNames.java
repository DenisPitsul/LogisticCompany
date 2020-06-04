package com.solvd.logistic_company.helper;

public class CityNames {
    private String cityFromName;
    private String cityToName;

    public CityNames(String cityFromName, String cityToName) {
        this.cityFromName = cityFromName;
        this.cityToName = cityToName;
    }

    public CityNames() { }

    public String getCityFromName() {
        return cityFromName;
    }

    public void setCityFromName(String cityFromName) {
        this.cityFromName = cityFromName;
    }

    public String getCityToName() {
        return cityToName;
    }

    public void setCityToName(String cityToName) {
        this.cityToName = cityToName;
    }

    @Override
    public String toString() {
        return "CityNames{" +
                "cityFromName='" + cityFromName + '\'' +
                ", cityToName='" + cityToName + '\'' +
                '}';
    }
}
