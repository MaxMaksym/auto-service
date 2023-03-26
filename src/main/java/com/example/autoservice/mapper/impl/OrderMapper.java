package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.OrderRequestDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.model.Service;
import com.example.autoservice.service.CarService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;

    @Override
    public Order toModel(OrderRequestDto requestDto) {
        Order order = new Order();
        order.setCar(carService.findById(requestDto.getCarId()));
        order.setDescription(requestDto.getDescription());
        return order;
    }

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setCarId(order.getCar().getId());
        responseDto.setDescription(order.getDescription());
        responseDto.setCreationDate(order.getCreationDate());
        responseDto.setTotalCost(order.getTotalPrice());
        responseDto.setCompletionDate(order.getCompletionDate());
        responseDto.setStatus(order.getStatus().name());
        if (order.getServices() != null) {
            responseDto.setServiceIds(order.getServices().stream()
                    .map(Service::getId)
                    .collect(Collectors.toList()));
        }
        if (order.getProducts() != null) {
            responseDto.setProductIds(order.getProducts().stream()
                    .map(Product::getId)
                    .collect(Collectors.toList()));
        }
        return responseDto;
    }
}
