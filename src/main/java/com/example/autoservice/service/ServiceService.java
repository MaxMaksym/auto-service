package com.example.autoservice.service;

import com.example.autoservice.model.Service;
import java.util.List;

public interface ServiceService extends GenericService<Service> {
    List<Service> findAllById(List<Long> ids);

    List<Service> findAllByMechanicId(Long id);
}
