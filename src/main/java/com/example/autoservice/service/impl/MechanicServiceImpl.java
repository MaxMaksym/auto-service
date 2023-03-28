package com.example.autoservice.service.impl;

import com.example.autoservice.model.Mechanic;
import com.example.autoservice.model.Service;
import com.example.autoservice.repository.MechanicRepository;
import com.example.autoservice.service.MechanicService;
import com.example.autoservice.service.ServiceService;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;
    private final ServiceService serviceService;

    @Override
    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic findById(Long id) {
        return mechanicRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find mechanic by id " + id));
    }

    @Override
    public BigDecimal calculateSalary(Long id) {
        BigDecimal salary = mechanicRepository.calculateSalary(id);
        List<Service> services = serviceService.findAllByMechanicId(id);
        services.stream()
                .peek(service -> service.setStatus(Service.Status.PAID))
                .forEach(serviceService::save);
        return salary;
    }
}
