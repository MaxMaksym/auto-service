package com.example.autoservice.mapper;

public interface ResponseDtoMapper<D, T> {
    D toDto(T t);
}
