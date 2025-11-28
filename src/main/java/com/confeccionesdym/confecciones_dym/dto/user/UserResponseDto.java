package com.confeccionesdym.confecciones_dym.dto.user;

public record UserResponseDto(
        Integer idUser,
        String dniUser,
        String nameUser,
        String lastNameUser,
        String roleUser,
        String phoneUser
) {
}