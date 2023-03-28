package com.example.autoservice.service;

import com.example.autoservice.model.Car;
import java.util.List;

public interface CarService extends GenericService<Car> {
    List<Car> findAllById(List<Long> carIds);
}
