package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.client.ClientRequestDto;
import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;

import java.util.List;

public interface ClienteService {
    List<ClientResponseDto> listAll();
    ClientResponseDto findById(Integer id);
    ClientResponseDto save(ClientRequestDto clientRequestDto);
    ClientResponseDto update(Integer id, ClientRequestDto clientRequestDto);
    void delete(Integer id);
}
