package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionRequestDto;
import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionResponseDto;
import com.confeccionesdym.confecciones_dym.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConfeccionService {
    List<ConfectionResponseDto> listAll();
    ConfectionResponseDto findById(Integer id);
    ConfectionResponseDto save(ConfectionRequestDto confectionRequestDto);
    ConfectionResponseDto update(Integer id, ConfectionRequestDto confectionRequestDto);
    void delete(Integer id);

    PageResponse<ConfectionResponseDto> listPageConfections(int page, int elements);
}
