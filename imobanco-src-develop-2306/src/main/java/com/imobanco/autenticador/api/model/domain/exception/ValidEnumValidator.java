package com.imobanco.autenticador.api.model.domain.exception;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Permitir valores nulos (ou você pode ajustar isso conforme necessário)
        }

        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(value.name())) {
                return true;
            }
        }

        return false;
    }
}