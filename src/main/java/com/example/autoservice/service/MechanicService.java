package com.example.autoservice.service;

import com.example.autoservice.model.Mechanic;
import java.math.BigDecimal;

public interface MechanicService extends GenericService<Mechanic> {
    BigDecimal calculateSalary(Long id);
}
