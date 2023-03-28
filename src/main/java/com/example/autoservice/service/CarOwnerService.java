package com.example.autoservice.service;

import com.example.autoservice.model.CarOwner;

public interface CarOwnerService extends GenericService<CarOwner> {
    void addOrderToOwner(Long orderId, Long ownerId);

    int getAmountOfCarOwnerOrders(Long id);
}
