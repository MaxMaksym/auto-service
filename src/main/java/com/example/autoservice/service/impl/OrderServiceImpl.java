package com.example.autoservice.service.impl;

import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.model.Service;
import com.example.autoservice.repository.CarOwnerRepository;
import com.example.autoservice.repository.OrderRepository;
import com.example.autoservice.repository.ProductRepository;
import com.example.autoservice.repository.ServiceRepository;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.ServiceService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final double PRODUCT_COEFFICIENT = 0.01;
    private static final double SERVICE_COEFFICIENT = 0.02;
    private static final double MAX_DISCOUNT = 0.1;
    private static final String DIAGNOSIS_NAME = "diagnosis";
    private static final BigDecimal DIAGNOSIS_PRICE = BigDecimal.valueOf(500);
    private final OrderRepository orderRepository;
    private final CarOwnerRepository carOwnerRepository;
    private final ProductRepository productRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceService serviceService;

    @Override
    public Order add(Order order) {
        Service diagnosis = createNewDiagnosis();
        order.setCreationDate(LocalDate.now());
        order.setStatus(Order.Status.ACCEPTED);
        order.setServices(Set.of(diagnosis));
        Order orderFromDb = orderRepository.save(order);
        carOwnerRepository.addOrderToOwner(order.getId(), order.getCar().getCarOwner().getId());
        return orderFromDb;
    }

    @Override
    public Order update(Order order) {
        Order orderFromDb = orderRepository.findById(order.getId()).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id " + order.getId()));
        if (order.getStatus() != null
                && (order.getStatus().equals(Order.Status.SUCCESSFUL)
                || order.getStatus().equals(Order.Status.UNSUCCESSFUL))) {
            order.setCompletionDate(LocalDate.now());
        }
        orderFromDb.setCar(order.getCar());
        orderFromDb.setDescription(order.getDescription());
        Order updatedOrder = orderRepository.save(orderFromDb);
        orderRepository.addCarOwner(order.getId(), order.getCar().getCarOwner().getId());
        return updatedOrder;
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        return findById(id).getTotalPrice();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id " + id));
    }

    @Override
    public List<Order> findAllByCarOwnerId(Long carOwnerId) {
        return orderRepository.findAllByCarOwnerId(carOwnerId);
    }

    @Override
    public Order addProducts(Long id, List<Long> productIds) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id" + id));
        List<Product> products = productRepository.findAllById(productIds);
        products.forEach(order::addProduct);
        order.setTotalPrice(calculateTotalPrice(order));
        return orderRepository.save(order);
    }

    @Override
    public Order addServices(Long id, List<Long> serviceIds) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id" + id));
        order.getServices().stream()
                .filter(service -> service.getName().equals(DIAGNOSIS_NAME))
                .peek(service -> service.setPrice(BigDecimal.ZERO))
                .forEach(serviceService::update);
        List<Service> services = serviceRepository.findAllById(serviceIds);
        services.forEach(order::addService);
        order.setTotalPrice(calculateTotalPrice(order));
        return orderRepository.save(order);
    }

    private BigDecimal calculateTotalPrice(Order order) {
        int amountOfCarOwnerOrders =
                carOwnerRepository.getAmountOfCarOwnerOrders(order.getId());
        double productDiscountPercentage = PRODUCT_COEFFICIENT * amountOfCarOwnerOrders;
        double serviceDiscountPercentage = SERVICE_COEFFICIENT * amountOfCarOwnerOrders;
        BigDecimal productsPrice = order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal servicePrice = order.getServices().stream()
                .map(Service::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal productsDiscount = calculateDiscount(productDiscountPercentage, productsPrice);
        BigDecimal servicesDiscount = calculateDiscount(serviceDiscountPercentage, servicePrice);
        return productsPrice.subtract(productsDiscount)
                .add(servicePrice.subtract(servicesDiscount));
    }

    private BigDecimal calculateDiscount(double discountPercentage, BigDecimal price) {
        return discountPercentage > MAX_DISCOUNT
                ? price.multiply(BigDecimal.valueOf(MAX_DISCOUNT)) :
                price.multiply(BigDecimal.valueOf(discountPercentage));
    }

    private Service createNewDiagnosis() {
        Service diagnosis = new Service();
        diagnosis.setName(DIAGNOSIS_NAME);
        diagnosis.setPrice(DIAGNOSIS_PRICE);
        diagnosis.setStatus(Service.Status.UNPAID);
        return serviceService.add(diagnosis);
    }
}
