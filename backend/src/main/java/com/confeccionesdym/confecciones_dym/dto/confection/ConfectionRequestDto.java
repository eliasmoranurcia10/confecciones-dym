package com.confeccionesdym.confecciones_dym.dto.confection;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ConfectionRequestDto(
        @Size(min = 3, max = 45, message = "El nombre debe tener de 3 a 255 caracteres")
        String descriptionConfection,

        @NotBlank(message = "El estado de confección es requerido")
        @Pattern(
                regexp = "SOLICITADO|PROCESO|TERMINADO|ENTREGADO",
                message = "Solo se permiten valores como SOLICITADO, PROCESO, TERMINADO o ENTREGADO"
        )
        String statusConfection,

        @NotNull(message = "La fecha de entrega es obligatoria")
        @FutureOrPresent(message = "La fecha de entrega no puede ser anterior a hoy")
        LocalDate dateDelivery,

        @Size(min = 2, max = 255, message = "La ruta de la confección debe tener de 2 hasta 255 caracteres")
        String imgConfection,

        @NotNull(message = "El id de prenda es requerido")
        @Positive(message = "El id de prenda es un número positivo")
        Integer idGarment
) {
}
