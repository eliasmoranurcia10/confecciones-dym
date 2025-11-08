package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.sale.SaleRequestDto;
import com.confeccionesdym.confecciones_dym.dto.sale.SaleResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Venta;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, ClienteMapper.class, PrendaMapper.class })
public interface VentaMapper {

    @Mapping(target = "idVenta", ignore = true)
    @Mapping(target = "fechaEmision", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "tipoPago", source = "paymentType")
    @Mapping(target = "totalPago", source = "totalPayment")
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "prenda", ignore = true)
    Venta toVenta(SaleRequestDto saleRequestDto);

    @InheritConfiguration(name = "toVenta")
    void updateVentaFromDto(SaleRequestDto saleRequestDto, @MappingTarget Venta venta);

    @Mapping(target = "idSale", source = "idVenta")
    @Mapping(target = "emissionDate", source = "fechaEmision", qualifiedByName = "fechaEmisionToEmissionDate")
    @Mapping(target = "PaymentType", source = "tipoPago")
    @Mapping(target = "totalPayment", source = "totalPago")
    @Mapping(target = "userResponseDto", source = "usuario")
    @Mapping(target = "clientResponseDto", source = "cliente")
    @Mapping(target = "garmentResponseDto", source = "prenda")
    SaleResponseDto toSaleResponseDto(Venta venta);
    List<SaleResponseDto> toSalesResponseDto(List<Venta> ventas);

    @Named("fechaEmisionToEmissionDate")
    default String fechaEmisionToEmissionDate(LocalDateTime fechaEmision) {
        return fechaEmision==null ? null : fechaEmision.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        );
    }
}
