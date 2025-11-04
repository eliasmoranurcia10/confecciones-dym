package com.confeccionesdym.confecciones_dym.dto.user;

public record UserRequestDto(
        String dniUser,
        String nameUser,
        String lastNameUser,
        String roleUser,
        String phoneUser
) {
}
