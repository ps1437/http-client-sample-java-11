package com.syscho.api.client.model;

import java.util.List;

public class CityWeatherResponse {
    private String name;
    private String weather;
    private List<String> status;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s%n", this.getName(), this.getWeather(), status);

    }
}
