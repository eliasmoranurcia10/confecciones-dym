package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.sale.SaleRequestDto;
import com.confeccionesdym.confecciones_dym.dto.sale.SaleResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Venta;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VentaService {
    List<SaleResponseDto> listAll();
    SaleResponseDto findById(Integer id);
    SaleResponseDto save(SaleRequestDto saleRequestDto);
    SaleResponseDto update(Integer id, SaleRequestDto saleRequestDto);
    void delete(Integer id);

    List<SaleResponseDto> listAllByTipoPago(String tipoPago);
    List<SaleResponseDto> listByDateBetween(String fechaInicial, String fechaFinal);
    Page<SaleResponseDto> listPagSales(int page, int elements);
}
