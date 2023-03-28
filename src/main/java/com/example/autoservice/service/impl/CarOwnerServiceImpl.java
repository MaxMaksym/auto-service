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
    public CarOwner save(CarOwner carOwner) {
        CarOwner carOwnerFromDb = carOwnerRepository.save(carOwner);
        carOwnerFromDb.getCars().forEach(car -> car.setCarOwner(carOwnerFromDb));
        carOwnerFromDb.getCars().forEach(carService::save);
        return carOwnerFromDb;
    }

    @Override
    public void addOrderToOwner(Long orderId, Long ownerId) {
        carOwnerRepository.addOrderToOwner(orderId, ownerId);
    }

    @Override
    public int getAmountOfCarOwnerOrders(Long id) {
        return carOwnerRepository.getAmountOfCarOwnerOrders(id);
    }
}
