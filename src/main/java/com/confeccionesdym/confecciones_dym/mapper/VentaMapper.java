package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.sale.SaleRequestDto;
import com.confeccionesdym.confecciones_dym.dto.sale.SaleResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Venta;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, ClienteMapper.class, PrendaMapper.class })
public interface VentaMapper {

    @Mapping(target = "idVenta", ignore = true)
    @Mapping(target = "fechaEmision", source = "emissionDate")
    @Mapping(target = "tipoPago", source = "PaymentType")
    @Mapping(target = "totalPago", source = "totalPayment")
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "prenda", ignore = true)
    Venta toVenta(SaleRequestDto saleRequestDto);

    @InheritConfiguration(name = "toVenta")
    void updateVentaFromDto(SaleRequestDto saleRequestDto, @MappingTarget Venta venta);

    @Mapping(target = "idSale", source = "idVenta")
    @Mapping(target = "emissionDate", source = "fechaEmision")
    @Mapping(target = "PaymentType", source = "tipoPago")
    @Mapping(target = "totalPayment", source = "totalPago")
    @Mapping(target = "userResponseDto", source = "usuario")
    @Mapping(target = "clientResponseDto", source = "cliente")
    @Mapping(target = "garmentResponseDto", source = "prenda")
    SaleResponseDto toSaleResponseDto(Venta venta);
    List<SaleResponseDto> toSalesResponseDto(List<Venta> ventas);
}
