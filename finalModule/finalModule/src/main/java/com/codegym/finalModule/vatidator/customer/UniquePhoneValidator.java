package com.codegym.finalModule.vatidator.customer;

import com.codegym.finalModule.repository.ICustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    private final ICustomerRepository icustomerRepository;
    public UniquePhoneValidator(ICustomerRepository icustomerRepository) {
        this.icustomerRepository = icustomerRepository;
    }
    @Override
    public void initialize(UniquePhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return !this.icustomerRepository.existsByPhoneNumber(value);
    }
}
