package com.confeccionesdym.confecciones_dym.dto.garment;

import java.math.BigDecimal;

public record GarmentResponseDto(
        Integer idGarment,
        String typeGarment,
        String size,
        String collegeGarment,
        BigDecimal unitPrice,
        String imgGarment,
        String quantityStock
) {
}
