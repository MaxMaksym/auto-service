package com.example.autoservice.lib.validator;

import com.example.autoservice.lib.OrderStatus;
import com.example.autoservice.model.Order;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class OrderStatusValidator implements ConstraintValidator<OrderStatus, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(Order.Status.values())
                .anyMatch(value -> value.name().equals(status.toUpperCase()));
    }
}
