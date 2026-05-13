package com.confeccionesdym.confecciones_dym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //Deshabilitar la protección CSRF
                .csrf(AbstractHttpConfigurer::disable)
                //Habilitar CORS con la configuración predeterminada
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Ingresar con autenticación básica a cualquier endpoint -->  .anyRequest().authenticated()
                        // Para permitir el acceso sin autenticación --> .anyRequest().permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}