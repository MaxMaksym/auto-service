package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.ServiceRequestDto;
import com.example.autoservice.dto.response.ServiceResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Service;
import com.example.autoservice.service.MechanicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServiceMapper implements
        RequestDtoMapper<ServiceRequestDto, Service>,
        ResponseDtoMapper<ServiceResponseDto, Service> {
    private final MechanicService mechanicService;

    @Override
    public Service toModel(ServiceRequestDto requestDto) {
        Service service = new Service();
        service.setName(requestDto.getName());
        service.setMechanic(mechanicService.findById(requestDto.getMechanicId()));
        service.setPrice(requestDto.getPrice());
        return service;
    }

    @Override
    public ServiceResponseDto toDto(Service service) {
        ServiceResponseDto responseDto = new ServiceResponseDto();
        responseDto.setName(service.getName());
        responseDto.setId(service.getId());
        responseDto.setMechanicId(service.getMechanic().getId());
        responseDto.setPrice(service.getPrice());
        responseDto.setStatus(service.getStatus().name());
        return responseDto;
    }
}
