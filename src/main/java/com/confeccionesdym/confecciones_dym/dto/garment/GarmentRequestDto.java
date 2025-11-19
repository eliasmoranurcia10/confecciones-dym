package com.confeccionesdym.confecciones_dym.dto.garment;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record GarmentRequestDto(
        @NotBlank(message = "El tipo de brenda es requerido")
        @Size(min = 3, max = 45, message = "El tipo de prenda debe tener de 3 a 45 caracteres")
        @Pattern(
                regexp = "^(?! )[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+)*(?<! )$",
                message = "Solo se permiten letras (con acentos y ñ), números, guiones bajos y un solo espacio entre palabras"
        )
        String typeGarment,

        @Size(max = 3, message = "La talla puede tener hasta 3 caracteres")
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Solo letras o números")
        String size,

        @Size(max = 45, message = "El colegio de prenda puede tener hasta 45 caracteres")
        @Pattern(
                regexp = "^(?! )[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ0-9_]+)*(?<! )$",
                message = "Solo se permiten letras (con acentos y ñ), números, guiones bajos y un solo espacio entre palabras"
        )
        String collegeGarment,

        @Positive(message = "El precio es un número positivo")
        @Digits(integer = 10, fraction = 2, message = "Formato de precio incorrecto")
        BigDecimal unitPrice,

        @Size(min = 2, max = 255, message = "La ruta de la imagen debe tener de 2 hasta 255 caracteres")
        String imgGarment,

        @NotNull(message = "La cantidad de stock es requerida")
        @Positive(message = "La cantidad debe ser un número positivo")
        Integer quantityStock
) {
}
