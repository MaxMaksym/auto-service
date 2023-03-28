package com.example.autoservice.lib.validator;

import com.example.autoservice.lib.ServiceStatus;
import com.example.autoservice.model.Service;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ServiceStatusValidator implements ConstraintValidator<ServiceStatus, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(Service.Status.values())
                .anyMatch(value -> value.name().equals(status.toUpperCase()));
    }
}
