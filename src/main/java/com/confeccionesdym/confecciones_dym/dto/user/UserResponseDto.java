package com.confeccionesdym.confecciones_dym.dto.user;

public record UserResponseDto(
        Integer idUser,
        String dniUser,
        String nameUser,
        String lastNameUser,
        String username,
        String password,
        String roleUser,
        String phoneUser,
        Boolean locked,
        Boolean disabled
) {
}