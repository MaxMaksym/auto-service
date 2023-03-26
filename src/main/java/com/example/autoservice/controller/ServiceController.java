package com.example.autoservice.controller;

import com.example.autoservice.dto.request.ServiceRequestDto;
import com.example.autoservice.dto.response.ServiceResponseDto;
import com.example.autoservice.mapper.impl.ServiceMapper;
import com.example.autoservice.model.Service;
import com.example.autoservice.service.ServiceService;
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
@RequestMapping("/services")
@AllArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    @PostMapping
    @ApiOperation(value = "Add a new service",
            notes = "Adds a new service with the specified details.")
    public ServiceResponseDto add(@RequestBody @Valid ServiceRequestDto requestDto) {
        return serviceMapper.toDto(serviceService.add(serviceMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing service",
            notes = "Updates the service with the specified ID with the given details.")
    public ServiceResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ServiceRequestDto requestDto) {
        Service service = serviceMapper.toModel(requestDto);
        service.setId(id);
        return serviceMapper.toDto(serviceService.update(service));
    }

    @PutMapping("/status/{id}")
    @ApiOperation(value = "Update the status of a service",
            notes = "Updates the wasPaidToMechanic status of a service")
    public ServiceResponseDto updateStatus(@PathVariable Long id,
                                           @RequestBody Boolean wasPaidToMechanic) {
        Service service = serviceService.findById(id);
        service.setWasPaidToMechanic(wasPaidToMechanic);
        return serviceMapper.toDto(serviceService.update(service));
    }
}
