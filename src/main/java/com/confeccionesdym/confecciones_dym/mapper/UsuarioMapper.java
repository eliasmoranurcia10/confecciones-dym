package com.confeccionesdym.confecciones_dym.mapper;

import com.confeccionesdym.confecciones_dym.dto.user.UserRequestDto;
import com.confeccionesdym.confecciones_dym.dto.user.UserResponseDto;
import com.confeccionesdym.confecciones_dym.model.entity.Usuario;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "dniUsuario", source = "dniUser")
    @Mapping(target = "nombresUsuario", source = "nameUser")
    @Mapping(target = "apellidosUsuario", source = "lastNameUser")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "rolUsuario", source = "roleUser")
    @Mapping(target = "celularUsuario", source = "phoneUser")
    @Mapping(target = "locked", source = "locked")
    @Mapping(target = "disabled", source = "disabled")
    @Mapping(target = "ventas", ignore = true)
    Usuario toUsuario(UserRequestDto userRequestDto);

    @InheritConfiguration(name = "toUsuario")
    void updateUsuarioFromDto(UserRequestDto userRequestDto, @MappingTarget Usuario usuario);

    @Mapping(target = "idUser", source = "idUsuario")
    @Mapping(target = "dniUser", source = "dniUsuario")
    @Mapping(target = "nameUser", source = "nombresUsuario")
    @Mapping(target = "lastNameUser", source = "apellidosUsuario")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roleUser", source = "rolUsuario")
    @Mapping(target = "phoneUser", source = "celularUsuario")
    @Mapping(target = "locked", source = "locked")
    @Mapping(target = "disabled", source = "disabled")
    UserResponseDto toUserResponseDto(Usuario usuario);
    List<UserResponseDto> toUsersResponseDto(List<Usuario> usuarios);
}
