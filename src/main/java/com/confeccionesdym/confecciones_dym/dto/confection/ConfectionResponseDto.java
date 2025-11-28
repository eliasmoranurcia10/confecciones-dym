package com.confeccionesdym.confecciones_dym.dto.confection;

import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;

import java.time.LocalDate;

public record ConfectionResponseDto(
        Integer idConfection,
        String descriptionConfection,
        String statusConfection,
        String dateDelivery,
        String imgConfection,
        GarmentResponseDto garmentResponseDto
) {
}
