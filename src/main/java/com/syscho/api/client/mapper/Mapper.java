package com.syscho.api.client.mapper;

public interface Mapper<T> {

    T convert(String response);

}
