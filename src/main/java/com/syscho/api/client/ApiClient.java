package com.syscho.api.client;

import com.google.gson.Gson;
import com.syscho.api.client.model.PostModelRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/weather/search";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private final Gson GSON = new Gson();

    public HttpResponse<String> get() throws IOException, InterruptedException {
        return sendRequest(BASE_URL);
    }

    public HttpResponse<String> post(PostModelRequest postRequest) throws IOException, InterruptedException {
        String jsonBody = GSON.toJson(postRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .build();
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> deletePost(int postId) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + postId))
                .method("DELETE", HttpRequest.BodyPublishers.noBody())
                .build();
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }


    public HttpResponse<String> get(String keyword) throws IOException, InterruptedException {
        String url = String.format("%s?name=%s",
                BASE_URL,
                URLEncoder.encode(keyword, StandardCharsets.UTF_8));
        return sendRequest(url);
    }

    public HttpResponse<String> get(String keyword, int page) throws IOException, InterruptedException {
        String url = String.format("%s?name=%s&page=%d",
                BASE_URL,
                URLEncoder.encode(keyword, StandardCharsets.UTF_8),
                page);
        System.out.println(url);
        return sendRequest(url);
    }

    private HttpResponse<String> sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
