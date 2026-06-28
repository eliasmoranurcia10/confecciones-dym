package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.measure.MeasureRequestDto;
import com.confeccionesdym.confecciones_dym.dto.measure.MeasureResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Medida;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, ConfeccionMapper.class})
public interface MedidaMapper {

    @Mapping(target = "idMedida", ignore = true)
    @Mapping(target = "espalda", source = "back")
    @Mapping(target = "busto", source = "bust")
    @Mapping(target = "cintura", source = "waist")
    @Mapping(target = "talleDelantero", source = "frontLength")
    @Mapping(target = "talleEspalda", source = "backLength")
    @Mapping(target = "contornoBusto", source = "bustCircumference")
    @Mapping(target = "contornoCintura", source = "waistCircumference")
    @Mapping(target = "talleCorto", source = "shortLength")
    @Mapping(target = "altoBusto", source = "bustHeight")
    @Mapping(target = "separacionBusto", source = "bustSeparation")
    @Mapping(target = "escote", source = "neckline")
    @Mapping(target = "largoManga", source = "sleeveLength")
    @Mapping(target = "punio", source = "fist")
    @Mapping(target = "largoFalda", source = "skirtLength")
    @Mapping(target = "otrosDetalles", source = "otherDetails")
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "confeccion", ignore = true)
    Medida toMedida(MeasureRequestDto measureRequestDto);

    @InheritConfiguration(name = "toMedida")
    void updateMedidaFromDto(MeasureRequestDto measureRequestDto, @MappingTarget Medida medida);

    @Mapping(target = "idMeasure", source = "idMedida")
    @Mapping(target = "back", source = "espalda")
    @Mapping(target = "bust", source = "busto")
    @Mapping(target = "waist", source = "cintura")
    @Mapping(target = "frontLength", source = "talleDelantero")
    @Mapping(target = "backLength", source = "talleEspalda")
    @Mapping(target = "bustCircumference", source = "contornoBusto")
    @Mapping(target = "waistCircumference", source = "contornoCintura")
    @Mapping(target = "shortLength", source = "talleCorto")
    @Mapping(target = "bustHeight", source = "altoBusto")
    @Mapping(target = "bustSeparation", source = "separacionBusto")
    @Mapping(target = "neckline", source = "escote")
    @Mapping(target = "sleeveLength", source = "largoManga")
    @Mapping(target = "fist", source = "punio")
    @Mapping(target = "skirtLength", source = "largoFalda")
    @Mapping(target = "otherDetails", source = "otrosDetalles")
    @Mapping(target = "clientResponseDto", source = "cliente")
    @Mapping(target = "confectionResponseDto", source = "confeccion")
    MeasureResponseDto toMeasureResponseDto(Medida medida);
    List<MeasureResponseDto> toMeasuresResponseDto(List<Medida> medidas);
}
