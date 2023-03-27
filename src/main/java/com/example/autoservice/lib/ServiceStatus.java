package com.example.autoservice.lib;

import com.example.autoservice.lib.validator.ServiceStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ServiceStatusValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceStatus {
    String message() default "Invalid status";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
