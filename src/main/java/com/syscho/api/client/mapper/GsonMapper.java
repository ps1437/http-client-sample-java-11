package com.syscho.api.client.mapper;

import com.google.gson.Gson;

public class GsonMapper<T> implements Mapper<T> {
    private final Gson gson = new Gson();
    private final Class<T> type;

    public GsonMapper(Class<T> type) {
        this.type = type;
    }

    @Override
    public T convert(String response) {
        return gson.fromJson(response, type);
    }
}
