package com.example.autoservice.model;

import com.example.autoservice.model.abstraction.PersonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mechanics")
@Getter
@Setter
public class Mechanic extends PersonEntity {
    @OneToMany(mappedBy = "mechanic")
    private List<Service> services;

    public void addService(Service service) {
        services.add(service);
        service.setMechanic(this);
    }

    public void removeService(Service service) {
        services.remove(service);
        service.setMechanic(null);
    }
}
