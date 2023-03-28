package com.example.autoservice.controller;

import com.example.autoservice.dto.request.CarRequestDto;
import com.example.autoservice.dto.response.CarResponseDto;
import com.example.autoservice.mapper.impl.CarMapper;
import com.example.autoservice.model.Car;
import com.example.autoservice.service.CarService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private final CarMapper carMapper;
    private final CarService carService;

    @PostMapping
    @ApiOperation(value = "Add a new car",
            notes = "Creates and returns a new car "
                    + "based on the information provided in the request body.")
    public CarResponseDto add(@RequestBody @Valid CarRequestDto requestDto) {
        return carMapper.toDto(carService.save(carMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a car by ID",
            notes = "Updates the details of an existing car with the specified ID.")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody @Valid CarRequestDto requestDto) {
        Car car = carMapper.toModel(requestDto);
        car.setId(id);
        return carMapper.toDto(carService.save(car));
    }
}
