package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.config.JwtUtil;
import com.confeccionesdym.confecciones_dym.dto.login.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    private ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {

        // Crear un objeto de autenticación con el nombre de usuario y la contraseña proporcionados
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(
                loginDto.username(), loginDto.password()
        );
        // Autenticar al usuario utilizando el AuthenticationManager
        // Si la autenticación es exitosa, se devolverá un objeto Authentication con los detalles del usuario autenticado
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        // Si la autenticación no es exitosa, devolver un error 401 Unauthorized
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Si la autenticación es exitosa, generar un token JWT y devolverlo en el encabezado de la respuesta
        String jwt = this.jwtUtil.create(loginDto.username());

        // Devolver el token JWT en el encabezado de la respuesta
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
