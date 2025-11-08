package com.confeccionesdym.confecciones_dym.dto.sale;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record SaleRequestDto(
        @NotBlank(message = "El tipo de pago es requerido")
        @Size(min = 3, max = 45, message = "El nombre debe tener de 3 a 45 caracteres")
        @Pattern(
                regexp = "YAPE|EFECTIVO|PLIN|TRANSFERENCIA",
                message = "Solo se permiten valores como YAPE, EFECTIVO, PLIN o TRANSFERENCIA"
        )
        String paymentType,

        @NotNull(message = "El total de pago es requerido")
        @Positive(message = "El pago debe ser un número positivo")
        @Digits(integer = 10, fraction = 2, message = "El total de pago debe tener 10 dígitos enteros y 2 dígitos fraccionarios")
        BigDecimal totalPayment,

        @NotNull(message = "El id de usuario es requerido")
        @Positive(message = "El id de usuario es un número positivo")
        Integer idUser,

        @NotNull(message = "El id del cliente es requerido")
        @Positive(message = "El id del cliente es un número positivo")
        Integer idClient,

        @NotNull(message = "El id de prenda es requerido")
        @Positive(message = "El id de prenda es un número positivo")
        Integer idGarment
) {
}
