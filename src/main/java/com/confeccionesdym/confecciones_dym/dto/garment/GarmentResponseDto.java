package com.confeccionesdym.confecciones_dym.dto.garment;

public record GarmentResponseDto(
        Integer idGarment,
        String typeGarment,
        String size,
        String collegeGarment,
        String imgGarment,
        String quantityStock
) {
}
