package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.garment.GarmentRequestDto;
import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;
import com.confeccionesdym.confecciones_dym.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PrendaService {
    List<GarmentResponseDto> listAll();
    GarmentResponseDto findById(Integer id);
    GarmentResponseDto save(GarmentRequestDto garmentRequestDto);
    GarmentResponseDto update(Integer id, GarmentRequestDto garmentRequestDto);
    void delete(Integer id);

    List<GarmentResponseDto> listByCollege(String college);
    List<GarmentResponseDto> listByLessStock();
    List<GarmentResponseDto> listByGarmentTypeLessStock(String garmentType);
    PageResponse<GarmentResponseDto> listPageGarments(int page, int elements);
}
