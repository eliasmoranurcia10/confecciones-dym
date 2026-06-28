package com.confeccionesdym.confecciones_dym.dto.client;

import java.math.BigDecimal;

public record SaleClientResponseDto(
        Integer idSale,
        String emissionDate,
        String PaymentType,
        BigDecimal totalPayment
) {
}
