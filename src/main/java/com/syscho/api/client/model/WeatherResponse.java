package com.syscho.api.client.model;

import java.util.List;

public class WeatherResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<CityWeatherResponse> data;

    // Getters and Setters
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getPer_page() { return per_page; }
    public void setPer_page(int per_page) { this.per_page = per_page; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public int getTotal_pages() { return total_pages; }
    public void setTotal_pages(int total_pages) { this.total_pages = total_pages; }

    public List<CityWeatherResponse> getData() { return data; }
    public void setData(List<CityWeatherResponse> data) { this.data = data; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %-15s %-20s%n", "City", "Weather", "Status"));
        sb.append("-------------------------------------------------------------\n");

        for (CityWeatherResponse city : data) {
            String status = String.join(", ", city.getStatus());
            sb.append(String.format("%-15s %-15s %-20s%n", city.getName(), city.getWeather(), status));
        }

        return sb.toString();
    }
}
