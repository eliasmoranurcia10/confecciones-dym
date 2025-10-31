package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.garment.GarmentRequestDto;
import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Prenda;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PrendaMapper {

    @Mapping(target = "idPrenda", ignore = true)
    @Mapping(target = "tipoPrenda", source = "typeGarment")
    @Mapping(target = "talla", source = "size")
    @Mapping(target = "colegioPrenda", source = "collegeGarment")
    @Mapping(target = "imgPrenda", source = "imgGarment")
    @Mapping(target = "cantidadStock", source = "quantityStock")
    @Mapping(target = "ventas", ignore = true)
    @Mapping(target = "confecciones", ignore = true)
    Prenda toPrenda(GarmentRequestDto garmentRequestDto);

    @InheritConfiguration(name = "toPrenda")
    void updatePrendaFromDto(GarmentRequestDto garmentRequestDto, @MappingTarget Prenda prenda);

    @Mapping(target = "idGarment", source = "idPrenda")
    @Mapping(target = "typeGarment", source = "tipoPrenda")
    @Mapping(target = "size", source = "talla")
    @Mapping(target = "collegeGarment", source = "colegioPrenda")
    @Mapping(target = "imgGarment", source = "imgPrenda")
    @Mapping(target = "quantityStock", source = "cantidadStock")
    GarmentResponseDto toGarmentResponseDto(Prenda prenda);
}
