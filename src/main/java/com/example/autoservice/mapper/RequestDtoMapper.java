package com.example.autoservice.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D requestDto);
}
