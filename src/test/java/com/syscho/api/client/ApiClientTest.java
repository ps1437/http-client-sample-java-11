package com.syscho.api.client;

import com.syscho.api.client.model.PostModelRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiClientTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    void testGetWeatherApi() throws IOException, InterruptedException {
        HttpResponse<String> response = apiClient.get();


        assertTrue(response.statusCode() == 200, "Expected status code 200 but got " + response.statusCode());
    }

    @Test
    void testPostApi() throws IOException, InterruptedException {
        PostModelRequest postRequest = new PostModelRequest("1 piece", "One piece is real", 1);

        HttpResponse<String> response = apiClient.post(postRequest);

        assertTrue(response.body().contains("\"userId\": 1"), "Response body should contain 'id: 101'");

        assertTrue(response.statusCode() == 201, "Expected status code 201 but got " + response.statusCode());
    }
}
