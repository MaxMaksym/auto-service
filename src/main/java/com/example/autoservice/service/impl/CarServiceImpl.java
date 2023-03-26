package com.example.autoservice.service.impl;

import com.example.autoservice.model.Car;
import com.example.autoservice.repository.CarRepository;
import com.example.autoservice.service.CarService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car add(Car entity) {
        return carRepository.save(entity);
    }

    @Override
    public Car update(Car entity) {
        return carRepository.save(entity);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find car by id" + id));
    }
}
