package com.example.autoservice.service;

import com.example.autoservice.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends GenericService<Order> {
    BigDecimal getTotalPrice(Long id);

    List<Order> findAllByCarOwnerId(Long carOwnerId);

    Order addProducts(Long id, List<Long> productIds);

    Order addServices(Long id, List<Long> serviceIds);
}
