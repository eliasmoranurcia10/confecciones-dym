package com.confeccionesdym.confecciones_dym.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 45, message = "El nombre debe tener de 3 a 45 caracteres")
        @Pattern(
                regexp = "^(?! )[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+)*(?<! )$",
                message = "Solo se permiten letras (con acentos y ñ), números, guiones bajos y un solo espacio entre palabras"
        )
        String nameClient,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 3, max = 45, message = "El apellido debe tener de 3 a 45 caracteres")
        @Pattern(
                regexp = "^(?! )[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+)*(?<! )$",
                message = "Solo se permiten letras (con acentos y ñ), números, guiones bajos y un solo espacio entre palabras"
        )
        String lastNameClient,

        @NotBlank(message = "El número de teléfono es obligatorio")
        @Pattern(regexp = "\\d{9}", message = "El número de celular debe tener 9 dígitos")
        String phoneClient
) {
}
