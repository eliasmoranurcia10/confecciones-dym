package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.client.ClientRequestDto;
import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;
import com.confeccionesdym.confecciones_dym.dto.client.SaleClientResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Cliente;
import com.confeccionesdym.confecciones_dym.model.entity.Venta;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "nombresCliente", source = "nameClient")
    @Mapping(target = "apellidosCliente", source = "lastNameClient")
    @Mapping(target = "celularCliente", source = "phoneClient")
    @Mapping(target = "ventas", ignore = true)
    @Mapping(target = "medidas", ignore = true)
    Cliente toCliente(ClientRequestDto clientRequestDto);

    @InheritConfiguration(name = "toCliente")
    void updateClienteFromDto(ClientRequestDto clientRequestDto, @MappingTarget Cliente cliente);

    @Mapping(target = "idClient", source = "idCliente")
    @Mapping(target = "nameClient", source = "nombresCliente")
    @Mapping(target = "lastNameClient", source = "apellidosCliente")
    @Mapping(target = "phoneClient", source = "celularCliente")
    @Mapping(target = "salesClientResponseDto", source = "ventas" )
    ClientResponseDto toClientResponseDto(Cliente cliente);
    List<ClientResponseDto> toClientsResponseDto(List<Cliente> clientes);

    @Mapping(target = "idSale", source = "idVenta")
    @Mapping(target = "emissionDate", source = "fechaEmision", qualifiedByName = "fechaEmisionToEmissionDate")
    @Mapping(target = "PaymentType", source = "tipoPago")
    @Mapping(target = "totalPayment", source = "totalPago")
    SaleClientResponseDto toSaleClientResponseDto(Venta venta);
    List<SaleClientResponseDto> toSaleClientResponseDto(List<Venta> ventas);

    @Named("fechaEmisionToEmissionDate")
    default String fechaEmisionToEmissionDate(LocalDateTime fechaEmision) {
        return fechaEmision==null ? null : fechaEmision.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        );
    }
}
