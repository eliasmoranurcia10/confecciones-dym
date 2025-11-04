package com.confeccionesdym.confecciones_dym.dto.confection;

import java.time.LocalDate;

public record ConfectionRequestDto(
        String descriptionConfection,
        String statusConfection,
        LocalDate dateDelivery,
        String imgConfection,
        Integer idGarment
) {
}
