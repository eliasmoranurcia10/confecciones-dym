package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.dto.response.PageResponse;
import com.confeccionesdym.confecciones_dym.dto.user.UserRequestDto;
import com.confeccionesdym.confecciones_dym.dto.user.UserResponseDto;
import com.confeccionesdym.confecciones_dym.exception.*;
import com.confeccionesdym.confecciones_dym.mapper.UsuarioMapper;
import com.confeccionesdym.confecciones_dym.model.entity.Usuario;
import com.confeccionesdym.confecciones_dym.repository.UsuarioRepository;
import com.confeccionesdym.confecciones_dym.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

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
            // Proceso de encriptación de la contraseña antes de guardar el usuario
            Usuario usuario = this.usuarioMapper.toUsuario(userRequestDto);
            usuario.setPassword(new BCryptPasswordEncoder().encode(userRequestDto.password()));
            return this.usuarioMapper.toUserResponseDto(this.usuarioRepository.save(usuario));
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
                    usuario.setPassword(new BCryptPasswordEncoder().encode(userRequestDto.password()));
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
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró al usuario con el id: " + id)
        );
        try {
            this.usuarioRepository.delete(usuario);
            this.usuarioRepository.flush();
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConflictException("No se puede eliminar el usuario porque tiene registros asociados");
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al eliminar el usuario");
        }
    }

    @Override
    public PageResponse<UserResponseDto> listPagUsers(int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);
        Page<UserResponseDto> pagina = this.usuarioRepository.findAll(pageable).map(this.usuarioMapper::toUserResponseDto);
        return new PageResponse<>(
                pagina.getContent(),
                pagina.getNumber(),
                pagina.getSize(),
                pagina.getTotalElements(),
                pagina.getTotalPages()
        );
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No se encontró al usuario con el username: " + username)
        );
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRolUsuario())
                .accountLocked(usuario.getLocked())
                .disabled(usuario.getDisabled())
                .build();
    }
}
