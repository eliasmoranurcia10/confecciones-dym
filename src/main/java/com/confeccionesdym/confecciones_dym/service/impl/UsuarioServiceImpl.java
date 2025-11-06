package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.dto.user.UserRequestDto;
import com.confeccionesdym.confecciones_dym.dto.user.UserResponseDto;
import com.confeccionesdym.confecciones_dym.exception.BadRequestException;
import com.confeccionesdym.confecciones_dym.exception.DuplicateResourceException;
import com.confeccionesdym.confecciones_dym.exception.InternalServerErrorException;
import com.confeccionesdym.confecciones_dym.exception.ResourceNotFoundException;
import com.confeccionesdym.confecciones_dym.mapper.UsuarioMapper;
import com.confeccionesdym.confecciones_dym.repository.UsuarioRepository;
import com.confeccionesdym.confecciones_dym.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UserResponseDto> listAll() {
        return this.usuarioMapper.toUsersResponseDto(this.usuarioRepository.findAll());
    }

    @Override
    public UserResponseDto findById(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.usuarioMapper.toUserResponseDto(this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró al usuario con el id: "+id)
        ));
    }

    @Override
    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        try {
            return this.usuarioMapper.toUserResponseDto(this.usuarioRepository.save(this.usuarioMapper.toUsuario(userRequestDto)));
        }
        catch (DataIntegrityViolationException exception) {
            throw new DuplicateResourceException("Valores del usuario ya registrados");
        }
        catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al guardar usuario");
        }
    }

    @Override
    @Transactional
    public UserResponseDto update(Integer id, UserRequestDto userRequestDto) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.usuarioMapper.toUserResponseDto(this.usuarioRepository.findById(id)
                .map(usuario -> {
                    this.usuarioMapper.updateUsuarioFromDto(userRequestDto, usuario);
                    try {
                        return this.usuarioRepository.save(usuario);
                    }
                    catch (DataIntegrityViolationException exception) {
                        throw new DuplicateResourceException("Valores del usuario ya registrados");
                    }
                    catch (Exception ex) {
                        throw new InternalServerErrorException("Error inesperado al guardar usuario");
                    }
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró al usuario con el id: "+id)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        try {
            this.usuarioRepository.delete(this.usuarioRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("No se encontró al usuario con el id: " + id)
            ));
        } catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al eliminar el usuario");
        }
    }
}
