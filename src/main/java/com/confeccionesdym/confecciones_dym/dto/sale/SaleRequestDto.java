package com.confeccionesdym.confecciones_dym.dto.sale;

import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;
import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;
import com.confeccionesdym.confecciones_dym.dto.user.UserResponseDto;

import java.math.BigDecimal;

public record SaleRequestDto(
        String paymentType,
        BigDecimal totalPayment,
        Integer idUser,
        Integer idClient,
        Integer idGarment
) {
}
