package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.client.ClientRequestDto;
import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Cliente;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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
    ClientResponseDto toClientResponseDto(Cliente cliente);
    List<ClientResponseDto> toClientsResponseDto(List<Cliente> clientes);
}
