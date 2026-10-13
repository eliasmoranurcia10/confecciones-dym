package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.config.JwtUtil;
import com.confeccionesdym.confecciones_dym.dto.login.LoginDto;
import com.confeccionesdym.confecciones_dym.service.AuthService;
import com.confeccionesdym.confecciones_dym.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public String login(LoginDto loginDto) {

        // Crear un objeto de autenticación con el nombre de usuario y la contraseña proporcionados
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(
                loginDto.username(), loginDto.password()
        );

        // Autenticar al usuario utilizando el AuthenticationManager
        // Si la autenticación es exitosa, se devolverá un objeto Authentication con los detalles del usuario autenticado
        Authentication authentication = this.authenticationManager.authenticate(login);


        // Si la autenticación es exitosa, generar un token JWT y devolverlo en el encabezado de la respuesta
        String jwt = this.jwtUtil.create(loginDto.username());

        return jwt;
    }
}
