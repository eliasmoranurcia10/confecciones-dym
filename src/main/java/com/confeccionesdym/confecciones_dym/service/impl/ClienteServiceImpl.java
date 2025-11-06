package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.dto.client.ClientRequestDto;
import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;
import com.confeccionesdym.confecciones_dym.exception.BadRequestException;
import com.confeccionesdym.confecciones_dym.exception.DuplicateResourceException;
import com.confeccionesdym.confecciones_dym.exception.InternalServerErrorException;
import com.confeccionesdym.confecciones_dym.exception.ResourceNotFoundException;
import com.confeccionesdym.confecciones_dym.mapper.ClienteMapper;
import com.confeccionesdym.confecciones_dym.model.entity.Cliente;
import com.confeccionesdym.confecciones_dym.repository.ClienteRepository;
import com.confeccionesdym.confecciones_dym.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<ClientResponseDto> listAll() {
        return this.clienteMapper.toClientsResponseDto(this.clienteRepository.findAll());
    }

    @Override
    public ClientResponseDto findById(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.clienteMapper.toClientResponseDto(this.clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró al cliente con id: "+ id)
        ));
    }

    @Override
    @Transactional
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        try {
            return this.clienteMapper.toClientResponseDto(this.clienteRepository.save(this.clienteMapper.toCliente(clientRequestDto)));
        }
        catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("Se encontró duplicado de valores en el cliente");
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al guardar el cliente");
        }
    }

    @Override
    @Transactional
    public ClientResponseDto update(Integer id, ClientRequestDto clientRequestDto) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.clienteRepository.findById(id)
                .map( cliente -> {
                    this.clienteMapper.updateClienteFromDto(clientRequestDto, cliente);
                    try {
                        return this.clienteMapper.toClientResponseDto(this.clienteRepository.save(cliente));
                    }
                    catch (DataIntegrityViolationException ex) {
                        throw new DuplicateResourceException("Se encontró duplicado de valores en el cliente");
                    }
                    catch (Exception exception) {
                        throw new InternalServerErrorException("Error inesperado al actualizar el cliente");
                    }
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró al cliente con id: " +id)
                );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró al cliente con id: " +id)
        );
        try {
            this.clienteRepository.delete(cliente);
        } catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al eliminar al cliente");
        }
    }
}
