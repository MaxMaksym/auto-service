package com.example.autoservice.service.impl;

import com.example.autoservice.model.Service;
import com.example.autoservice.repository.ServiceRepository;
import com.example.autoservice.service.ServiceService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public Service add(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service update(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service findById(Long id) {
        return serviceRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find service by id " + id));
    }
}
