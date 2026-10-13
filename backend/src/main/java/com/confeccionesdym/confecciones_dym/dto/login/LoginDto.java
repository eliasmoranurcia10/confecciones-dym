package com.confeccionesdym.confecciones_dym.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "Se requiere el username del usuario")
        @Size(min = 3, max = 20, message = "El username debe tener de 3 a 20 caracteres")
        // El username no puede contener espacios
        @Pattern(
                regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+$",
                message = "El username no puede contener espacios y solo se permiten letras (con acentos y ñ), números y guiones bajos"
        )
        String username,

        @NotBlank(message = "Se requiere el password del usuario")
        @Size(min = 8, max = 255, message = "El password debe tener al menos 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+\\-=]{8,}$",
                message = "El password debe contener al menos una letra, un número y puede incluir caracteres especiales"
        )
        String password
) {
}
