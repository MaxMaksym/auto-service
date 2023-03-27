package com.example.autoservice.model;

import com.example.autoservice.model.abstraction.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car_owners")
@Getter
@Setter
public class CarOwner extends Person {
    @OneToMany(mappedBy = "carOwner",
            orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "car_owner_id")
    private List<Order> orders = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
        car.setCarOwner(this);
    }

    public void addAllCars(List<Car> newCars) {
        newCars.forEach(this::addCar);
    }
}
