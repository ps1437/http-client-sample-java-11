package com.syscho.api.client;

import com.syscho.api.client.mapper.GsonMapper;
import com.syscho.api.client.mapper.JacksonMapper;
import com.syscho.api.client.mapper.Mapper;
import com.syscho.api.client.model.CityWeatherResponse;
import com.syscho.api.client.model.PostModelRequest;
import com.syscho.api.client.model.WeatherResponse;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherApiMain {

    private static final ApiClient apiClient = new ApiClient();


    public static void main(String[] args) throws IOException, InterruptedException {
/*
        get();
        System.out.println("========================");
        get("ind");
        getWithPagination("all");*/
//        PostRequest();

        delete();
    }

    private static void delete() throws IOException, InterruptedException {
        HttpResponse<String> response = apiClient.deletePost(1);
        System.out.println(response.body());
    }

    private static void PostRequest() throws IOException, InterruptedException {
        PostModelRequest postRequest = new PostModelRequest();
        postRequest.setBody("hello");
        postRequest.setTitle("No one ever");
        postRequest.setUserId(1);
        HttpResponse<String> response = apiClient.post(postRequest);
        Mapper<PostModelRequest> mapper = new JacksonMapper<>(PostModelRequest.class);
        PostModelRequest postResponse = mapper.convert(response.body());
        System.out.println("--------------- USING JACKSON--------------------------");
        System.out.println(postResponse);

    }

    private static void getWithPagination(String keyword) throws IOException, InterruptedException {

        List<WeatherResponse> weatherResponseList = new LinkedList<>();

        int currentPage = 1;
        while (true) {
            HttpResponse<String> response = apiClient.get(keyword, currentPage);
            if (response.statusCode() == 200) {
                Mapper<WeatherResponse> gsonMapper = new GsonMapper<>(WeatherResponse.class);
                WeatherResponse weatherResponse = gsonMapper.convert(response.body());
                System.out.println("weatherResponse" + weatherResponse);
                if (weatherResponse.getData().isEmpty()) {
                    break;
                }
                weatherResponseList.add(weatherResponse);
                System.out.println("currentPage : " + currentPage++);
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("--------------PAGINATION EXAMPLE-------------------------");
        System.out.println("--------------------------------------------");

        List<CityWeatherResponse> cityWeatherResponses = weatherResponseList.stream()
                .flatMap(weatherResponse -> weatherResponse.getData().stream())
                .collect(Collectors.toList());
        System.out.println(cityWeatherResponses);

    }

    private static void get() throws IOException, InterruptedException {
        HttpResponse<String> response = apiClient.get();
        printAndExtractUsingFastXmlJsonMapper(response);
        printAndExtractUsingGson(response);
    }

    private static void get(String keyword) throws IOException, InterruptedException {
        HttpResponse<String> response = apiClient.get(keyword);
        printAndExtractUsingFastXmlJsonMapper(response);
        printAndExtractUsingGson(response);
    }


    private static void printAndExtractUsingFastXmlJsonMapper(HttpResponse<String> response) {
        Mapper<WeatherResponse> mapper = new JacksonMapper<>(WeatherResponse.class);
        WeatherResponse weatherResponse = mapper.convert(response.body());
        System.out.println("--------------- USING JACKSON--------------------------");
        System.out.println(weatherResponse);

    }

    private static void printAndExtractUsingGson(HttpResponse<String> response) {
        Mapper<WeatherResponse> gsonMapper = new GsonMapper<>(WeatherResponse.class);
        WeatherResponse weatherResponse = gsonMapper.convert(response.body());

        System.out.println("--------------- USING GSON--------------------------");
        System.out.println(weatherResponse);
    }


}
