package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.user.UserRequestDto;
import com.confeccionesdym.confecciones_dym.dto.user.UserResponseDto;

import java.util.List;

public interface UsuarioService {
    List<UserResponseDto> listAll();
    UserResponseDto findById(Integer id);
    UserResponseDto save(UserRequestDto userRequestDto);
    UserResponseDto update(Integer id, UserRequestDto userRequestDto);
    void delete(Integer id);
}
