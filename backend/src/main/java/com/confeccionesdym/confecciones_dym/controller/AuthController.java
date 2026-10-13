package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.config.JwtUtil;
import com.confeccionesdym.confecciones_dym.dto.login.LoginDto;
import com.confeccionesdym.confecciones_dym.service.AuthService;
import jakarta.validation.Valid;
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

    private final AuthService authService;

    @PostMapping("/login")
    private ResponseEntity<Void> login(@RequestBody @Valid LoginDto loginDto) {
        // Devolver el token JWT en el encabezado de la respuesta
        String jwt = authService.login(loginDto);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
