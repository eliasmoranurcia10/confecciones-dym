package com.confeccionesdym.confecciones_dym.dto.measure;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MeasureRequestDto(
        @ValidMeasure
        BigDecimal back,

        @ValidMeasure
        BigDecimal bust,

        @ValidMeasure
        BigDecimal waist,

        @ValidMeasure
        BigDecimal frontLength,

        @ValidMeasure
        BigDecimal backLength,

        @ValidMeasure
        BigDecimal bustCircumference,

        @ValidMeasure
        BigDecimal waistCircumference,

        @ValidMeasure
        BigDecimal shortLength,

        @ValidMeasure
        BigDecimal bustHeight,

        @ValidMeasure
        BigDecimal bustSeparation,

        @ValidMeasure
        BigDecimal neckline,

        @ValidMeasure
        BigDecimal sleeveLength,

        @ValidMeasure
        BigDecimal fist,

        @ValidMeasure
        BigDecimal skirtLength,

        @Size(min = 2, max = 45, message = "El detalle debe tener de 2 a 255 caracteres")
        String otherDetails,

        @NotNull(message = "Se requiere el id del cliente")
        @Positive(message = "El id del cliente debe ser un número positivo")
        Integer idClient,

        @NotNull(message = "Se requiere el id de la confección")
        @Positive(message = "El id de la confección debe ser un número positivo")
        Integer idConfection
) {
}
