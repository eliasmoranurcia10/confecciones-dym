package com.confeccionesdym.confecciones_dym.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequestDto(
        @NotBlank(message = "El dni es obligatorio")
        @Pattern(regexp = "\\d{8}", message = "El número de dni debe tener 8 dígitos")
        String dniUser,

        @NotBlank(message = "Se requiere el nombre del usuario")
        String nameUser,

        @NotBlank(message = "Se requiere el apellidos del usuario")
        String lastNameUser,

        @NotBlank(message = "Se requiere el rol del usuario")
        String roleUser,

        @NotBlank(message = "Celular del usuario requerido")
        @Pattern(regexp = "\\d{9}", message = "El número de celular debe tener 9 dígitos")
        String phoneUser
) {
}
