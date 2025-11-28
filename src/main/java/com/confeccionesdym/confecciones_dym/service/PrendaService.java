package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.garment.GarmentRequestDto;
import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;

import java.util.List;

public interface PrendaService {
    List<GarmentResponseDto> listAll();
    GarmentResponseDto findById(Integer id);
    GarmentResponseDto save(GarmentRequestDto garmentRequestDto);
    GarmentResponseDto update(Integer id, GarmentRequestDto garmentRequestDto);
    void delete(Integer id);
}
