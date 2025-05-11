package com.syscho.api.client.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapper<T> implements Mapper {

   private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    public JacksonMapper(Class<T> type) {
        this.type = type;
    }

    @Override
    public T convert(String response) {
        try {
            return objectMapper.readValue(response, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
