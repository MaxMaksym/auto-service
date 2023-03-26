package com.example.autoservice.controller;

import com.example.autoservice.dto.request.ListOfProductsDto;
import com.example.autoservice.dto.request.ListOfServicesDto;
import com.example.autoservice.dto.request.OrderRequestDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.lib.Status;
import com.example.autoservice.mapper.impl.OrderMapper;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @ApiOperation(value = "Add a new order",
            notes = "Adds a new order with the specified details.")
    public OrderResponseDto add(@RequestBody @Valid OrderRequestDto requestDto) {
        return orderMapper.toDto(orderService.add(orderMapper.toModel(requestDto)));
    }

    @PostMapping("/products/{id}")
    @ApiOperation(value = "Add products to an order",
            notes = "Adds the specified products to an existing order with the specified ID.")
    public OrderResponseDto addProducts(@PathVariable Long id,
                                        @RequestBody ListOfProductsDto requestDto) {
        return orderMapper.toDto(orderService.addProducts(id, requestDto.getProductIds()));
    }

    @PostMapping("/services/{id}")
    @ApiOperation(value = "Add services to an order",
            notes = "Adds the specified services to an existing order with the specified ID.")
    public OrderResponseDto addServices(@PathVariable Long id,
                            @RequestBody ListOfServicesDto requestDto) {
        return orderMapper.toDto(orderService.addServices(id, requestDto.getServiceIds()));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing order",
            notes = "Updates an existing order with the specified ID and details.")
    public OrderResponseDto update(@PathVariable Long id,
                                   @RequestBody @Valid OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setId(id);
        return orderMapper.toDto(orderService.update(order));
    }

    @PutMapping("/status/{id}")
    @ApiOperation(value = "Update the status of an order",
            notes = "Updates the status of an existing order with the specified ID.")
    public OrderResponseDto updateStatus(@PathVariable Long id,
                                         @RequestBody @Status String status) {
        Order order = orderService.findById(id);
        order.setStatus(Order.Status.getStatus(status.toLowerCase()));
        return orderMapper.toDto(orderService.update(order));
    }

    @GetMapping("/total-price/{id}")
    @ApiOperation(value = "Get the total price of an order",
            notes = "Returns the total price of an existing order with the specified ID.")
    public BigDecimal getTotalPrice(@PathVariable Long id) {
        return orderService.getTotalPrice(id);
    }
}
