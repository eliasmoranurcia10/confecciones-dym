package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.measure.MeasureRequestDto;
import com.confeccionesdym.confecciones_dym.dto.measure.MeasureResponseDto;
import com.confeccionesdym.confecciones_dym.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MedidaService {
    List<MeasureResponseDto> listAll();
    MeasureResponseDto findById(Integer id);
    MeasureResponseDto save(MeasureRequestDto measureRequestDto);
    MeasureResponseDto update(Integer id, MeasureRequestDto measureRequestDto);
    void delete(Integer id);

    PageResponse<MeasureResponseDto> listPageMeasures(int page, int elements);
}
