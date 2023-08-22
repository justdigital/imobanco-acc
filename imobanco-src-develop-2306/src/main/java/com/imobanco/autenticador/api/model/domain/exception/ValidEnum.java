package com.imobanco.autenticador.api.model.domain.exception;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = ValidEnumValidator.class)
    @Documented
    public @interface ValidEnum {
        String message() default "Valor inválido para o enum";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
        Class<? extends Enum<?>> enumClass();
    }



