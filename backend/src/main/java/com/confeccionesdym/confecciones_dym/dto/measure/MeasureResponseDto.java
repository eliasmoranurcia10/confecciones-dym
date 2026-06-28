package com.confeccionesdym.confecciones_dym.dto.measure;

import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;
import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionResponseDto;

import java.math.BigDecimal;

public record MeasureResponseDto(
        Integer idMeasure,
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
        ClientResponseDto clientResponseDto,
        ConfectionResponseDto confectionResponseDto
) {
}
