package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.MechanicRequestDto;
import com.example.autoservice.dto.response.MechanicResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Mechanic;
import com.example.autoservice.model.Service;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MechanicMapper implements RequestDtoMapper<MechanicRequestDto, Mechanic>,
        ResponseDtoMapper<MechanicResponseDto, Mechanic> {
    @Override
    public Mechanic toModel(MechanicRequestDto requestDto) {
        Mechanic mechanic = new Mechanic();
        mechanic.setFullName(requestDto.getFullName());
        return mechanic;
    }

    @Override
    public MechanicResponseDto toDto(Mechanic mechanic) {
        MechanicResponseDto responseDto = new MechanicResponseDto();
        responseDto.setId(mechanic.getId());
        responseDto.setFullName(mechanic.getFullName());
        if (mechanic.getServices() != null) {
            responseDto.setServiceIds(mechanic.getServices().stream()
                    .map(Service::getId)
                    .collect(Collectors.toList()));
        }
        return responseDto;
    }
}
