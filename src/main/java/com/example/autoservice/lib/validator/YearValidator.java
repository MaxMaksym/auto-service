package com.example.autoservice.lib.validator;

import com.example.autoservice.lib.Year;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class YearValidator implements ConstraintValidator<Year, Integer> {
    private static final int MIN_CAR_YEAR = 1950;

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext constraintValidatorContext) {
        return year > MIN_CAR_YEAR && year < LocalDate.now().getYear();
    }
}
