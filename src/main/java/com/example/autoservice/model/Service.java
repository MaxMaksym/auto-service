package com.example.autoservice.model;

import com.example.autoservice.model.abstraction.Offering;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "services")
@Getter
@Setter
public class Service extends Offering {
    @ManyToOne(fetch = FetchType.LAZY)
    private Mechanic mechanic;
    private Boolean wasPaidToMechanic;

    public void addMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
        mechanic.addService(this);
    }

    public void removeMechanic(Mechanic mechanic) {
        this.mechanic = null;
        mechanic.removeService(this);
    }
}
