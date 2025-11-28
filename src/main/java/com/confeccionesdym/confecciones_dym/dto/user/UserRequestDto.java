package com.confeccionesdym.confecciones_dym.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "El dni es obligatorio")
        @Pattern(regexp = "\\d{8}", message = "El número de dni debe tener 8 dígitos")
        String dniUser,

        @NotBlank(message = "Se requiere el nombre del usuario")
        @Size(min = 3, max = 45, message = "El nombre debe tener de 3 a 45 caracteres")
        @Pattern(
                regexp = "^(?! )[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+)*(?<! )$",
                message = "Solo se permiten letras (con acentos y ñ), números, guiones bajos y un solo espacio entre palabras"
        )
        String nameUser,

        @NotBlank(message = "Se requiere el apellidos del usuario")
        @Size(min = 3, max = 45, message = "El apellido debe tener de 3 a 45 caracteres")
        @Pattern(
                regexp = "^(?! )[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+)*(?<! )$",
                message = "Solo se permiten letras (con acentos y ñ), números, guiones bajos y un solo espacio entre palabras"
        )
        String lastNameUser,

        @NotBlank(message = "Se requiere el rol del usuario")
        @Size(min = 3, max = 45, message = "El rol debe tener de 3 a 45 caracteres")
        @Pattern(
                regexp = "^(?! )[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+)*(?<! )$",
                message = "Solo se permiten letras (con acentos y ñ), números, guiones bajos y un solo espacio entre palabras"
        )
        String roleUser,

        @NotBlank(message = "Celular del usuario requerido")
        @Pattern(regexp = "\\d{9}", message = "El número de celular debe tener 9 dígitos")
        String phoneUser
) {
}
