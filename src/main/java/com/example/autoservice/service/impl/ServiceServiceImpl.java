package com.example.autoservice.service.impl;

import com.example.autoservice.model.Service;
import com.example.autoservice.repository.ServiceRepository;
import com.example.autoservice.service.ServiceService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public Service save(Service service) {
        if (service.getStatus() == null) {
            service.setStatus(Service.Status.UNPAID);
        }
        return serviceRepository.save(service);
    }

    @Override
    public Service findById(Long id) {
        return serviceRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find service by id " + id));
    }

    @Override
    public List<Service> findAllById(List<Long> ids) {
        return serviceRepository.findAllById(ids);
    }

    @Override
    public List<Service> findAllByMechanicId(Long id) {
        return serviceRepository.findAllByMechanicId(id);
    }
}
