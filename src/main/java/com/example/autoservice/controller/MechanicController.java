package com.example.autoservice.controller;

import com.example.autoservice.dto.request.MechanicRequestDto;
import com.example.autoservice.dto.response.MechanicResponseDto;
import com.example.autoservice.mapper.impl.MechanicMapper;
import com.example.autoservice.model.Mechanic;
import com.example.autoservice.service.MechanicService;
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
@RequestMapping("/mechanics")
@AllArgsConstructor
public class MechanicController {
    private final MechanicService mechanicService;
    private final MechanicMapper mechanicMapper;

    @PostMapping
    @ApiOperation(value = "Add a new mechanic",
            notes = "Adds a new mechanic with the specified details.")
    public MechanicResponseDto add(@RequestBody @Valid MechanicRequestDto requestDto) {
        return mechanicMapper.toDto(mechanicService.add(mechanicMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a mechanic by ID",
            notes = "Updates the details of an existing mechanic with the specified ID.")
    public MechanicResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid MechanicRequestDto requestDto) {
        Mechanic mechanic = mechanicMapper.toModel(requestDto);
        mechanic.setId(id);
        return mechanicMapper.toDto(mechanicService.update(mechanic));
    }

    @GetMapping("/salary/{id}")
    @ApiOperation(value = "Calculate salary for a mechanic",
            notes = "Calculates the salary for the mechanic with the specified ID.")
    public BigDecimal getSalary(@PathVariable Long id) {
        return mechanicService.calculateSalary(id);
    }
}
