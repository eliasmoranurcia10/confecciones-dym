package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionRequestDto;
import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Confeccion;
import org.mapstruct.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = {PrendaMapper.class})
public interface ConfeccionMapper {

    @Mapping(target = "idConfeccion", ignore = true)
    @Mapping(target = "descripcionConfeccion", source = "descriptionConfection")
    @Mapping(target = "estadoConfeccion", source = "statusConfection")
    @Mapping(target = "fechaEntrega", source = "dateDelivery")
    @Mapping(target = "imgConfeccion", source = "imgConfection")
    @Mapping(target = "prenda", ignore = true)
    @Mapping(target = "medidas", ignore = true)
    Confeccion toConfeccion(ConfectionRequestDto confectionRequestDto);

    @InheritConfiguration(name = "toConfeccion")
    void updateConfeccionFromDto(ConfectionRequestDto confectionRequestDto, @MappingTarget Confeccion confeccion);

    @Mapping(target = "idConfection", source = "idConfeccion")
    @Mapping(target = "descriptionConfection", source = "descripcionConfeccion")
    @Mapping(target = "statusConfection", source = "estadoConfeccion")
    @Mapping(target = "dateDelivery", source = "fechaEntrega", qualifiedByName = "fechaEntregaToDateDelivery")
    @Mapping(target = "imgConfection", source = "imgConfeccion")
    @Mapping(target = "garmentResponseDto", source = "prenda")
    ConfectionResponseDto toConfectionResponseDto(Confeccion confeccion);
    List<ConfectionResponseDto> toConfectionsResponseDto(List<Confeccion> confecciones);

    @Named("fechaEntregaToDateDelivery")
    default String fechaEntregaToDateDelivery(LocalDate fechaEntrega) {
        return fechaEntrega == null ? null: fechaEntrega.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );
    }
}
