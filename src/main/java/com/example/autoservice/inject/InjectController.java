package com.example.autoservice.inject;

import com.example.autoservice.model.Car;
import com.example.autoservice.model.CarOwner;
import com.example.autoservice.model.Mechanic;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.model.Service;
import com.example.autoservice.service.CarOwnerService;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.MechanicService;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.ProductService;
import com.example.autoservice.service.ServiceService;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InjectController {
    private final CarService carService;
    private final ProductService productService;
    private final MechanicService mechanicService;
    private final CarOwnerService carOwnerService;
    private final OrderService orderService;
    private final ServiceService serviceService;

    @PostConstruct
    void injectTestData() {
        List<Car> cars = generateCars();
        cars.replaceAll(carService::save);
        List<CarOwner> carOwners = generateCarOwners(cars);
        carOwners.replaceAll(carOwnerService::save);
        cars.replaceAll(car -> carService.findById(car.getId()));
        List<Mechanic> mechanics = generateMechanics();
        mechanics.replaceAll(mechanicService::save);
        List<Product> products = generateProducts();
        products.replaceAll(productService::save);
        List<Service> services = generateServices(mechanics);
        services.replaceAll(serviceService::save);
        List<Order> orders = generateOrders(cars);
        orders.replaceAll(orderService::save);
        orderService.addProducts(1L, List.of(1L, 2L));
        orderService.addServices(1L, List.of(1L, 2L));
        orderService.addProducts(2L, List.of(3L, 4L));
        orderService.addServices(2L, List.of(3L, 4L));
    }

    private List<Car> generateCars() {
        Car firstCar = new Car();
        firstCar.setManufacturer("Toyota");
        firstCar.setModel("Camry");
        firstCar.setYear(2020);
        firstCar.setNumber("CA-1234-AB");
        Car secondCar = new Car();
        secondCar.setManufacturer("Honda");
        secondCar.setModel("Civic");
        secondCar.setYear(2018);
        secondCar.setNumber("CA-5678-CD");
        Car thirdCar = new Car();
        thirdCar.setManufacturer("Ford");
        thirdCar.setModel("Mustang");
        thirdCar.setYear(2017);
        thirdCar.setNumber("CA-9012-EF");
        Car forthCar = new Car();
        forthCar.setManufacturer("Chevrolet");
        forthCar.setModel("Impala");
        forthCar.setYear(2016);
        forthCar.setNumber("CA-3456-GH");
        List<Car> cars = new ArrayList<>();
        cars.add(firstCar);
        cars.add(secondCar);
        cars.add(thirdCar);
        cars.add(forthCar);
        return cars;
    }

    private List<CarOwner> generateCarOwners(List<Car> cars) {
        CarOwner firstCarOwner = new CarOwner();
        firstCarOwner.setFullName("Bob");
        firstCarOwner.setCars(List.of(cars.get(0), cars.get(1)));
        CarOwner secondCarOwner = new CarOwner();
        secondCarOwner.setFullName("Alice");
        secondCarOwner.setCars(List.of(cars.get(2), cars.get(3)));
        List<CarOwner> carOwners = new ArrayList<>();
        carOwners.add(firstCarOwner);
        carOwners.add(secondCarOwner);
        return carOwners;
    }

    private List<Product> generateProducts() {
        Product firstProduct = new Product();
        firstProduct.setName("Car Oil");
        firstProduct.setPrice(BigDecimal.valueOf(70));
        Product secondProduct = new Product();
        secondProduct.setName("Brakes");
        secondProduct.setPrice(BigDecimal.valueOf(800));
        Product thirdProduct = new Product();
        thirdProduct.setName("Spark Plugs");
        thirdProduct.setPrice(BigDecimal.valueOf(1200));
        Product forthProduct = new Product();
        forthProduct.setName("Air Filter");
        forthProduct.setPrice(BigDecimal.valueOf(600));
        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        products.add(thirdProduct);
        products.add(forthProduct);
        return products;
    }

    private List<Mechanic> generateMechanics() {
        Mechanic firstMechanic = new Mechanic();
        firstMechanic.setFullName("Antonio");
        Mechanic secondMechanic = new Mechanic();
        secondMechanic.setFullName("Jack");
        List<Mechanic> mechanics = new ArrayList<>();
        mechanics.add(firstMechanic);
        mechanics.add(secondMechanic);
        return mechanics;
    }

    private List<Service> generateServices(List<Mechanic> mechanics) {
        Service firstService = new Service();
        firstService.setName("Wheel Alignment");
        firstService.setPrice(BigDecimal.valueOf(2500));
        firstService.setMechanic(mechanics.get(0));
        firstService.setStatus(Service.Status.UNPAID);
        Service secondService = new Service();
        secondService.setName("Battery Replacement");
        secondService.setPrice(BigDecimal.valueOf(5000));
        secondService.setMechanic(mechanics.get(0));
        secondService.setStatus(Service.Status.UNPAID);
        Service thirdService = new Service();
        thirdService.setName("Brake Pad Replacement");
        thirdService.setPrice(BigDecimal.valueOf(900));
        thirdService.setMechanic(mechanics.get(1));
        thirdService.setStatus(Service.Status.UNPAID);
        Service fourthService = new Service();
        fourthService.setName("Radiator Flush");
        fourthService.setPrice(BigDecimal.valueOf(1200));
        fourthService.setMechanic(mechanics.get(1));
        fourthService.setStatus(Service.Status.UNPAID);
        List<Service> services = new ArrayList<>();
        services.add(firstService);
        services.add(secondService);
        services.add(thirdService);
        services.add(fourthService);
        return services;
    }

    private List<Order> generateOrders(List<Car> cars) {
        Order firstOrder = new Order();
        firstOrder.setDescription("Problems with wheels and battery");
        firstOrder.setCar(cars.get(1));
        Order secondOrder = new Order();
        secondOrder.setDescription("Problems with brakes and radiator");
        secondOrder.setCar(cars.get(3));
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        return orders;
    }
}
