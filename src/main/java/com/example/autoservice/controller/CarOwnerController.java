package com.example.autoservice.controller;

import com.example.autoservice.dto.request.CarOwnerRequestDto;
import com.example.autoservice.dto.response.CarOwnerResponseDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.mapper.impl.CarOwnerMapper;
import com.example.autoservice.mapper.impl.OrderMapper;
import com.example.autoservice.model.CarOwner;
import com.example.autoservice.service.CarOwnerService;
import com.example.autoservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-owners")
@AllArgsConstructor
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final OrderService orderService;
    private final CarOwnerMapper carOwnerMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    @ApiOperation(value = "Add a new car owner",
            notes = "Adds a new car owner with the specified details.")
    public CarOwnerResponseDto add(@RequestBody @Valid CarOwnerRequestDto requestDto) {
        return carOwnerMapper.toDto(carOwnerService.add(carOwnerMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a car owner by ID",
            notes = "Updates the details of an existing car owner with the specified ID.")
    public CarOwnerResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CarOwnerRequestDto requestDto) {
        CarOwner carOwner = carOwnerMapper.toModel(requestDto);
        carOwner.setId(id);
        return carOwnerMapper.toDto(carOwnerService.update(carOwner));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all orders for a car owner",
            notes = "Returns a list of all orders associated with the specified car owner ID.")
    public List<OrderResponseDto> getOrders(@PathVariable Long id) {
        return orderService.findAllByCarOwnerId(id).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
