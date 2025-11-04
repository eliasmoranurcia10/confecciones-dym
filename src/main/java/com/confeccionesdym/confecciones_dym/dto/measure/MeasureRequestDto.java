package com.confeccionesdym.confecciones_dym.dto.measure;

import java.math.BigDecimal;

public record MeasureRequestDto(
        BigDecimal back,
        BigDecimal bust,
        BigDecimal waist,
        BigDecimal frontLength,
        BigDecimal backLength,
        BigDecimal bustCircumference,
        BigDecimal waistCircumference,
        BigDecimal shortLength,
        BigDecimal bustHeight,
        BigDecimal bustSeparation,
        BigDecimal neckline,
        BigDecimal sleeveLength,
        BigDecimal fist,
        BigDecimal skirtLength,
        String otherDetails,
        Integer idClient,
        Integer idConfection
) {
}
