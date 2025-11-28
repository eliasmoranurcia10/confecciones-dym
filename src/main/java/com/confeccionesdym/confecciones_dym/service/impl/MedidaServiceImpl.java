package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.dto.measure.MeasureRequestDto;
import com.confeccionesdym.confecciones_dym.dto.measure.MeasureResponseDto;
import com.confeccionesdym.confecciones_dym.exception.BadRequestException;
import com.confeccionesdym.confecciones_dym.exception.DuplicateResourceException;
import com.confeccionesdym.confecciones_dym.exception.InternalServerErrorException;
import com.confeccionesdym.confecciones_dym.exception.ResourceNotFoundException;
import com.confeccionesdym.confecciones_dym.mapper.MedidaMapper;
import com.confeccionesdym.confecciones_dym.model.entity.Cliente;
import com.confeccionesdym.confecciones_dym.model.entity.Confeccion;
import com.confeccionesdym.confecciones_dym.model.entity.Medida;
import com.confeccionesdym.confecciones_dym.repository.ClienteRepository;
import com.confeccionesdym.confecciones_dym.repository.ConfeccionRepository;
import com.confeccionesdym.confecciones_dym.repository.MedidaRepository;
import com.confeccionesdym.confecciones_dym.service.MedidaService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MedidaServiceImpl implements MedidaService {

    private final MedidaRepository medidaRepository;
    private final MedidaMapper medidaMapper;
    private final ClienteRepository clienteRepository;
    private final ConfeccionRepository confeccionRepository;

    @Override
    public List<MeasureResponseDto> listAll() {
        return this.medidaMapper.toMeasuresResponseDto(this.medidaRepository.findAll());
    }

    @Override
    public MeasureResponseDto findById(Integer id) {
        if(id==0 || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.medidaMapper.toMeasureResponseDto(this.medidaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la medida con el id: "+id)
        ));
    }

    @Override
    @Transactional
    public MeasureResponseDto save(MeasureRequestDto measureRequestDto) {

        Medida medida = this.medidaMapper.toMedida(measureRequestDto);

        asignarRelaciones(measureRequestDto, medida);

        try {
            return this.medidaMapper.toMeasureResponseDto(this.medidaRepository.save(medida));
        }
        catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("Medida ya registrada anteriormente");
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al guardar la medida");
        }
    }

    @Override
    @Transactional
    public MeasureResponseDto update(Integer id, MeasureRequestDto measureRequestDto) {
        if(id==0 || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.medidaMapper.toMeasureResponseDto(this.medidaRepository.findById(id)
                .map( medida -> {
                    this.medidaMapper.updateMedidaFromDto(measureRequestDto, medida);
                    asignarRelaciones(measureRequestDto, medida);
                    try {
                        return this.medidaRepository.save(medida);
                    }
                    catch (DataIntegrityViolationException ex) {
                        throw new DuplicateResourceException("Medida ya registrada anteriormente");
                    }
                    catch (Exception exception) {
                        throw new InternalServerErrorException("Error inesperado al actualizar la medida");
                    }
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró la medida con el id: "+id)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(id==0 || id<=0) throw new BadRequestException("El id es incorrecto");
        Medida medida = this.medidaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la medida con el id: "+id)
        );
        try {
            this.medidaRepository.delete(medida);
            this.medidaRepository.flush();
        }
        catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("No se puede eliminar la medida porque hubo conflicto");
        }
        catch (Exception exception){
            throw new InternalServerErrorException("Error inesperado al eliminar la medida");
        }
    }

    private void asignarRelaciones(@NotNull MeasureRequestDto measureRequestDto,@NotNull Medida medida) {
        Cliente cliente = this.clienteRepository.findById(measureRequestDto.idClient()).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró cliente con el id: "+ measureRequestDto.idClient())
        );
        medida.setCliente(cliente);

        Confeccion confeccion = this.confeccionRepository.findById(measureRequestDto.idConfection()).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró confección con el id: "+ measureRequestDto.idConfection())
        );
        medida.setConfeccion(confeccion);
    }
}
