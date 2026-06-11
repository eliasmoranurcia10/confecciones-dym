package com.confeccionesdym.confecciones_dym.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //Deshabilitar la protección CSRF
                .csrf(AbstractHttpConfigurer::disable)
                //Habilitar CORS con la configuración predeterminada
                .cors(Customizer.withDefaults())
                // Configurar la gestión de sesiones para que sea sin estado (stateless)
                // Esto es importante para aplicaciones RESTful que utilizan JWT,
                // ya que no se mantiene el estado de la sesión en el servidor
                .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Permitir iframes para H2 Console
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )
                .authorizeHttpRequests(auth -> auth
                        // Permitir el acceso a los endpoints de autenticación sin autenticación
                        .requestMatchers("/auth/**").permitAll()
                        // Permitir el acceso a los endpoints de Swagger sin autenticación
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll()
                        // Permitir el acceso a los endpoints de clientes sin autenticación
                        .requestMatchers(HttpMethod.GET, "/clients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/sales/**").hasRole("ADMIN")
                        // Permitir el acceso a los endpoints de clientes solo para usuarios con rol ADMIN
                        .requestMatchers(HttpMethod.POST, "/clients/**").hasRole("ADMIN")
                        // Ingresar con autenticación básica a cualquier endpoint -->  .anyRequest().authenticated()
                        // Para permitir el acceso sin autenticación --> .anyRequest().permitAll()
                        .anyRequest().authenticated()
                )
                // Agregar el filtro de JWT antes del filtro de autenticación de Spring Security
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}