package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.CarRequestDto;
import com.example.autoservice.dto.response.CarResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    @Override
    public Car toModel(CarRequestDto requestDto) {
        Car car = new Car();
        car.setManufacturer(requestDto.getManufacturer());
        car.setModel(requestDto.getModel());
        car.setYear(requestDto.getYear());
        car.setNumber(requestDto.getNumber());
        return car;
    }

    @Override
    public CarResponseDto toDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setManufacturer(car.getManufacturer());
        responseDto.setModel(car.getModel());
        responseDto.setYear(car.getYear());
        responseDto.setNumber(car.getNumber());
        return responseDto;
    }
}
