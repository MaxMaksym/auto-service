package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.CarOwnerRequestDto;
import com.example.autoservice.dto.response.CarOwnerResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Car;
import com.example.autoservice.model.CarOwner;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.CarService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarOwnerMapper implements RequestDtoMapper<CarOwnerRequestDto, CarOwner>,
        ResponseDtoMapper<CarOwnerResponseDto, CarOwner> {
    private final CarService carService;

    @Override
    public CarOwner toModel(CarOwnerRequestDto requestDto) {
        CarOwner carOwner = new CarOwner();
        carOwner.setFullName(requestDto.getFullName());
        List<Car> cars = carService.findAllById(requestDto.getCarIds());
        carOwner.addAllCars(cars);
        return carOwner;
    }

    @Override
    public CarOwnerResponseDto toDto(CarOwner carOwner) {
        CarOwnerResponseDto responseDto = new CarOwnerResponseDto();
        responseDto.setId(carOwner.getId());
        responseDto.setFullName(carOwner.getFullName());
        responseDto.setCarIds(carOwner.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        responseDto.setOrderIds(carOwner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
