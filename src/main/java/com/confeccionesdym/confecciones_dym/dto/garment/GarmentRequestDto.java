package com.confeccionesdym.confecciones_dym.dto.garment;

public record GarmentRequestDto(
        String typeGarment,
        String size,
        String collegeGarment,
        String imgGarment,
        String quantityStock
) {
}
