package com.example.autoservice.lib.validator;

import com.example.autoservice.lib.Status;
import com.example.autoservice.model.Order;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusValidator implements ConstraintValidator<Status, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(Order.Status.values())
                .anyMatch(value -> value.name().equals(status.toUpperCase()));
    }
}
