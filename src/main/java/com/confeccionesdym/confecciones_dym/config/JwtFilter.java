package com.confeccionesdym.confecciones_dym.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Validar que sea un Header de autorización válido
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader==null || authHeader.isEmpty() || authHeader.startsWith("Bearer") ){
            // Si no es un header de autorización válido, continuar con la cadena de filtros sin hacer nada
            filterChain.doFilter(request,response);
            return;
        }

        // Validar que el token sea válido
        String jwt = authHeader.split(" ")[1].trim();
        if(!this.jwtUtil.isValid(jwt)) {
            // Si el token no es válido, continuar con la cadena de filtros sin hacer nada
            filterChain.doFilter(request, response);
            return;
        }

        // Si el token es válido, cargar el usuario y establecer la autenticación en el contexto de seguridad
        String username = this.jwtUtil.getUsername(jwt);
        User user = (User) this.userDetailsService.loadUserByUsername(username);

        // Cargar el usuario y establecer la autenticación en el contexto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );

        // Enviar al contexto de seguridad la autenticación del usuario y continuar con la cadena de filtros
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
