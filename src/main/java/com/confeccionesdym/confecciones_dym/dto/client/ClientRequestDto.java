package com.confeccionesdym.confecciones_dym.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        String nameClient,

        @NotBlank(message = "El apellido es obligatorio")
        String lastNameClient,

        @NotBlank(message = "El número de teléfono es obligatorio")
        @Pattern(regexp = "\\d{9}", message = "El número de teléfono debe tener 9 dígitos")
        String phoneClient
) {
}
