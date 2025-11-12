package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionRequestDto;
import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionResponseDto;
import com.confeccionesdym.confecciones_dym.exception.*;
import com.confeccionesdym.confecciones_dym.mapper.ConfeccionMapper;
import com.confeccionesdym.confecciones_dym.model.entity.Confeccion;
import com.confeccionesdym.confecciones_dym.model.entity.Prenda;
import com.confeccionesdym.confecciones_dym.repository.ConfeccionRepository;
import com.confeccionesdym.confecciones_dym.repository.PrendaRepository;
import com.confeccionesdym.confecciones_dym.service.ConfeccionService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ConfeccionServiceImpl implements ConfeccionService {

    private final ConfeccionRepository confeccionRepository;
    private final ConfeccionMapper confeccionMapper;
    private final PrendaRepository prendaRepository;

    @Override
    public List<ConfectionResponseDto> listAll() {
        return this.confeccionMapper.toConfectionsResponseDto(this.confeccionRepository.findAll());
    }

    @Override
    public ConfectionResponseDto findById(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.confeccionMapper.toConfectionResponseDto(this.confeccionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la confección con el id: "+id)
        ));
    }

    @Override
    @Transactional
    public ConfectionResponseDto save(ConfectionRequestDto confectionRequestDto) {

        Confeccion confeccion = this.confeccionMapper.toConfeccion(confectionRequestDto);

        asignarRelaciones(confectionRequestDto, confeccion);

        try {
            return this.confeccionMapper.toConfectionResponseDto(this.confeccionRepository.save(confeccion));
        }
        catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("Confección ya registrada anteriormente");
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al guardar la confección");
        }
    }

    @Override
    @Transactional
    public ConfectionResponseDto update(Integer id, ConfectionRequestDto confectionRequestDto) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.confeccionMapper.toConfectionResponseDto(this.confeccionRepository.findById(id)
                .map(confeccion -> {
                    this.confeccionMapper.updateConfeccionFromDto(confectionRequestDto, confeccion);
                    asignarRelaciones(confectionRequestDto, confeccion);
                    try {
                        return this.confeccionRepository.save(confeccion);
                    }
                    catch (DataIntegrityViolationException ex) {
                        throw new DuplicateResourceException("Confección ya se encuentra registrada");
                    }
                    catch (Exception exception) {
                        throw new InternalServerErrorException("Error inesperado al actualizar la confección");
                    }
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró la confección con el id: "+id)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        Confeccion confeccion = this.confeccionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la confección con el id " + id)
        );
        try {
            this.confeccionRepository.delete(confeccion);
            this.confeccionRepository.flush();
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConflictException("No se puede elimicar la confección porque hubo conflicto");
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al eliminar la confección");
        }
    }

    private void asignarRelaciones(@NotNull ConfectionRequestDto confectionRequestDto,@NotNull Confeccion confeccion ) {
        Prenda prenda = this.prendaRepository.findById(confectionRequestDto.idGarment()).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la prenda con el id: " +confectionRequestDto.idGarment())
        );
        confeccion.setPrenda(prenda);
    }
}
