package com.confeccionesdym.confecciones_dym.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "dni_usuario", nullable = false, length = 8, unique = true)
    private String dniUsuario;

    @Column(name = "nombres_usuario", nullable = false, length = 45)
    private String nombresUsuario;

    @Column(name = "apellidos_usuario", nullable = false, length = 45)
    private String apellidosUsuario;

    @Column(name = "rol_usuario", nullable = false, length = 45)
    private String rolUsuario;

    @Column(name = "celular_usuario", nullable = false, length = 9, unique = true)
    private String celularUsuario;
}
