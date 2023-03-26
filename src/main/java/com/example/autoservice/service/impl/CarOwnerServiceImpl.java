package com.example.autoservice.service.impl;

import com.example.autoservice.model.CarOwner;
import com.example.autoservice.repository.CarOwnerRepository;
import com.example.autoservice.service.CarOwnerService;
import com.example.autoservice.service.CarService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerRepository carOwnerRepository;
    private final CarService carService;

    @Override
    public CarOwner findById(Long id) {
        return carOwnerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find car owner by id " + id));
    }

    @Override
    public CarOwner add(CarOwner carOwner) {
        CarOwner carOwnerFromDb = carOwnerRepository.save(carOwner);
        carOwnerFromDb.getCars().forEach(carService::update);
        return carOwnerFromDb;
    }

    @Override
    public CarOwner update(CarOwner carOwner) {
        CarOwner carOwnerFromDb = carOwnerRepository.save(carOwner);
        carOwnerFromDb.getCars().forEach(carService::update);
        return carOwnerFromDb;
    }
}
