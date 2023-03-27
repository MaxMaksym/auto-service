package com.example.autoservice.model;

import com.example.autoservice.model.abstraction.Person;
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
public class Mechanic extends Person {
    @OneToMany(mappedBy = "mechanic")
    private List<Service> services;
}
