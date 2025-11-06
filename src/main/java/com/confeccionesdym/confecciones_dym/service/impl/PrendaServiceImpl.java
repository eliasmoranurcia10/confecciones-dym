package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.dto.garment.GarmentRequestDto;
import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;
import com.confeccionesdym.confecciones_dym.exception.BadRequestException;
import com.confeccionesdym.confecciones_dym.exception.DuplicateResourceException;
import com.confeccionesdym.confecciones_dym.exception.InternalServerErrorException;
import com.confeccionesdym.confecciones_dym.exception.ResourceNotFoundException;
import com.confeccionesdym.confecciones_dym.mapper.PrendaMapper;
import com.confeccionesdym.confecciones_dym.repository.PrendaRepository;
import com.confeccionesdym.confecciones_dym.service.PrendaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PrendaServiceImpl implements PrendaService {

    private final PrendaRepository prendaRepository;
    private final PrendaMapper prendaMapper;

    @Override
    public List<GarmentResponseDto> listAll() {
        return this.prendaMapper.toGarmentsResponseDto(this.prendaRepository.findAll());
    }

    @Override
    public GarmentResponseDto findById(Integer id) {
        if(id==null || id <= 0) throw new BadRequestException("El id es incorrecto");
        return this.prendaMapper.toGarmentResponseDto(this.prendaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la prenda con el id: "+ id)
        ));
    }

    @Override
    @Transactional
    public GarmentResponseDto save(GarmentRequestDto garmentRequestDto) {
        try {
            return this.prendaMapper.toGarmentResponseDto(this.prendaRepository.save(this.prendaMapper.toPrenda(garmentRequestDto)));
        }
        catch (DataIntegrityViolationException exception) {
            throw new DuplicateResourceException("Prenda ya registrada");
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al registrar la prenda");
        }
    }

    @Override
    @Transactional
    public GarmentResponseDto update(Integer id, GarmentRequestDto garmentRequestDto) {
        if(id==null || id <= 0) throw new BadRequestException("el id es incorrecto");
        return this.prendaMapper.toGarmentResponseDto(this.prendaRepository.findById(id)
                .map( prenda -> {
                    this.prendaMapper.updatePrendaFromDto(garmentRequestDto, prenda);
                    try {
                        return this.prendaRepository.save(prenda);
                    }
                    catch (DataIntegrityViolationException exception) {
                        throw new DuplicateResourceException("Prenda ya registrada");
                    } catch (Exception ex) {
                        throw new InternalServerErrorException("Error inesperado al registrar la prenda");
                    }
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró la prenda con el id: " +id)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(id==null || id <= 0) throw new BadRequestException("el id es incorrecto");
        try {
            this.prendaRepository.delete(this.prendaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("No se encontró la prenda con el id: " + id)
            ));
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al eliminar la prenda");
        }
    }
}
