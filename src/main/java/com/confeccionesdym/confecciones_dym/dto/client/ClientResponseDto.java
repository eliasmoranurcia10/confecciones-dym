package com.confeccionesdym.confecciones_dym.dto.client;

public record ClientResponseDto (
        Integer idClient,
        String nameClient,
        String lastNameClient,
        String phoneClient
) {
}
