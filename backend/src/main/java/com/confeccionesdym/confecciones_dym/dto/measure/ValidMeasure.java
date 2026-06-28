package com.confeccionesdym.confecciones_dym.dto.measure;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

import java.lang.annotation.*;

@Positive(message = "La medida debe ser un número positivo")
@Digits(integer = 10, fraction = 2, message = "La medida puede tener hasta 10 dígitos enteros y 2 dígitos fraccionarios")
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidMeasure {
    String message() default "Medida inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
