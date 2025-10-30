package com.confeccionesdym.confecciones_dym.dto.sale;

import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;
import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;
import com.confeccionesdym.confecciones_dym.dto.user.UserResponseDto;

import java.math.BigDecimal;

public record SaleResponseDto(
        Integer idSale,
        String emissionDate,
        String PaymentType,
        BigDecimal totalPayment,
        UserResponseDto userResponseDto,
        ClientResponseDto clientResponseDto,
        GarmentResponseDto garmentResponseDto
) {
}
