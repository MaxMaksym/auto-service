package com.example.autoservice.lib.validator;

import com.example.autoservice.lib.Year;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class YearValidator implements ConstraintValidator<Year, Integer> {
    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext constraintValidatorContext) {
        return year > 1950 && year < LocalDate.now().getYear();
    }
}
