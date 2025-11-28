package com.confeccionesdym.confecciones_dym.dto.client;

import java.util.List;

public record ClientResponseDto (
        Integer idClient,
        String nameClient,
        String lastNameClient,
        String phoneClient,
        List<SaleClientResponseDto> salesClientResponseDto
) {
}
